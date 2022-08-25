package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CartPurchasingServiceTest {
    @Autowired
    CartPurchasingService cartPurchasingService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    LoyaltyCardRepository loyaltyCardRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;
    private final Long customerId = 1L;
    private final Long productId = 1L;
    private final Long secondProductId = 2L;

    Address expectedAddress = new Address("1120 N Ashland Ave", null, "Illinois", "Chicago", "60622");
    
    @Test
    public void orderCreatedFromCart() {
        cartRepository.addToCart(customerId, productId);
        cartRepository.addToCart(customerId, secondProductId);

        cartPurchasingService.purchaseCart(customerId);

        List<Order> allOrders = findAllOrders();

        assertThat(allOrders).hasSize(1);

        Order order = allOrders.get(0);
        assertThat(order.getCustomerId()).isEqualTo(customerId);
        assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("2667"));

        assertThat(order.lineItems).hasSize(2);
        assertThat(order.lineItems.get(0).getProductId()).isEqualTo(productId);
        assertThat(order.lineItems.get(1).getProductId()).isEqualTo(secondProductId);

        assertAddress(order.getAddress(), expectedAddress);

        assertThat(cartRepository.find(customerId).getProducts()).hasSize(0);
    }

    private void assertAddress(Address actual, Address expected) {
        assertThat(actual.streetAddress1).isEqualTo(expected.streetAddress1);
        assertThat(actual.streetAddress2).isEqualTo(expected.streetAddress2);
        assertThat(actual.stateName).isEqualTo(expected.stateName);
        assertThat(actual.city).isEqualTo(expected.city);
        assertThat(actual.zip).isEqualTo(expected.zip);
    }

    private List<Order> findAllOrders() {
        Map<Long, Order> orders = new HashMap<>();

        jdbcTemplate.query("select o.id, " +
                "o.street_address_1, " +
                "o.street_address_2, " +
                "o.state_name, " +
                "o.city, " +
                "o.zip, " +
                "customer_code, " +
                "shipping_cost, " +
                "product_id, " +
                "li.charged_price " +
                "from mrt_order o " +
                "join mrt_order_line_items li on li.mrt_order_id = o.id", (RowMapper<Order>) (rs, rowNum) -> {
            Long orderId = rs.getLong("id");

            if (orders.containsKey(orderId)) {
                Order order = orders.get(orderId);
                order.lineItems.add(new LineItem(rs.getLong("product_id"), rs.getBigDecimal("charged_price")));
            } else {
                List<LineItem> lineItems = new ArrayList<>();
                lineItems.add(new LineItem(rs.getLong("product_id"), rs.getBigDecimal("charged_price")));
                Order order = new Order(
                        rs.getLong("customer_code"),
                        rs.getBigDecimal("shipping_cost"),
                        new Address(rs.getString("street_address_1"),
                                rs.getString("street_address_2"),
                                rs.getString("state_name"),
                                rs.getString("city"),
                                rs.getString("zip")
                                ),
                        lineItems);
                orders.put(orderId, order);
            }

            return null;
        });

        return new ArrayList<>(orders.values());
    }

    @Test
    public void theShippingPriceIsUnchanged_whenThereAreNoStoresNearTheCustomer() {
        cartRepository.addToCart(customerId, productId);

        cartPurchasingService.purchaseCart(customerId);

        List<Order> allOrders = findAllOrders();

        Order order = allOrders.get(0);
        assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("2667"));
    }

    @Test
    public void theShippingIsFree_whenThereAreManyStoresNearby() {
        final Long customerNearThreeStores = 2L;

        cartRepository.addToCart(customerNearThreeStores, productId);
        cartPurchasingService.purchaseCart(customerNearThreeStores);

        List<Order> allOrders = findAllOrders();
        assertThat(allOrders).hasSize(1);
        assertThat(allOrders.get(0).getShippingPrice()).isEqualTo(new BigDecimal("0"));
    }

    @Test
    public void theShippingPriceIsFree_whenThereAreStoresNearTheCustomer() {
        final Long customerNearOneStore = 3L;
        Customer customer = customerRepository.findById(customerNearOneStore);

        cartRepository.addToCart(customerNearOneStore, productId);
        cartPurchasingService.purchaseCart(customer.getId());

        List<Order> allOrders  = findAllOrders();

        assertThat(allOrders).hasSize(1);
        Order order = allOrders.get(0);
        assertThat(order.getCustomerId()).isEqualTo(customer.getId());
        assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("0"));
    }

    @Test
    public void loyaltyCardPurchaseCountIncreases_whenLessThan10ProductPurchased() {
        final int numberOfPurchases = 2;

        LoyaltyCard loyaltyCard = loyaltyCardRepository.findOrCreateBy(customerId);
        final int startingCount = loyaltyCard.getPurchaseCount();

        cartRepository.addToCart(customerId, productId);
        cartPurchasingService.purchaseCart(customerId);

        cartRepository.addToCart(customerId, productId);
        cartPurchasingService.purchaseCart(customerId);

        LoyaltyCard updatedLoyaltyCard = loyaltyCardRepository.findOrCreateBy(customerId);
        final int endingCount = updatedLoyaltyCard.getPurchaseCount();

        assertThat(endingCount - numberOfPurchases).isEqualTo(startingCount);

        List<Order> allOrders = findAllOrders();
        Order lastOrder = allOrders.get(allOrders.size() - 1);
        assertThat(lastOrder.lineItems.get(0).getChargedPrice()).isNotEqualTo(new BigDecimal("0"));
    }

    @Test
    public void aProductIsFree_whenTheCustomerHasTenPurchases() throws Exception {
        final String todayDate = "11/05/1955";
        final String expectedDate = "08/31/1956";

        loyaltyCardRepository.findOrCreateBy(customerId);
        Calendar calendarWithDateToday = makeCalendar(todayDate);

        for (int i = 0; i < 11; i++) {
            cartRepository.addToCart(customerId, productId);
            cartPurchasingService.purchaseCart(customerId, calendarWithDateToday);
        }

        List<Order> allOrders = findAllOrders();

        Order order = allOrders.get(10);
        assertThat(order.getCustomerId()).isEqualTo(customerId);
        assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("2667"));
        assertThat(order.lineItems.get(0).getChargedPrice()).isEqualTo(new BigDecimal(0));
        assertThat(order.lineItems.get(0).getProductId()).isEqualTo(productId);

        LoyaltyCard loyaltyCard = loyaltyCardRepository.findOrCreateBy(customerId);
        assertThat(loyaltyCard.getPurchaseCount()).isEqualTo(0);

        assertDate(loyaltyCard.getExpiryEpoch(), expectedDate);
    }

    @Test
    public void tenPercentDiscountAppliedToAllProducts_whenCouponAddedToCart() throws Exception {
        final String knownCoupon = "FN2187";
        final String knownPriceWithDiscount = "8491";
        final BigDecimal discountedPrice = new BigDecimal(knownPriceWithDiscount);

        cartRepository.addCoupon(customerId, knownCoupon);

        cartRepository.addToCart(customerId, productId);
        cartRepository.addToCart(customerId, productId);

        cartPurchasingService.purchaseCart(customerId);

        List<Order> allOrders = findAllOrders();
        assertThat(allOrders).hasSize(1);

        Order order = allOrders.get(0);
        assertThat(order.lineItems.get(0).getChargedPrice()).isEqualTo(discountedPrice);
        assertThat(order.lineItems.get(1).getChargedPrice()).isEqualTo(discountedPrice);
    }

    private Calendar makeCalendar(String date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        calendar.setTime(simpleDateFormat.parse(date));
        return calendar;
    }

    private void assertDate(Date actualDate, String expectedDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        assertThat(simpleDateFormat.format(actualDate)).isEqualTo(expectedDate);
    }

    class LineItem {
        Long productId;
        BigDecimal chargedPrice;

        public LineItem(Long productId, BigDecimal chargedPrice) {
            this.productId = productId;
            this.chargedPrice = chargedPrice;
        }

        public Long getProductId() {
            return productId;
        }

        public BigDecimal getChargedPrice() {
            return chargedPrice;
        }
    }

    class Order {
        Long customerId;
        BigDecimal shippingPrice;
        private Address address;
        List<LineItem> lineItems;

        public Order(Long customerId, BigDecimal shippingPrice, Address address, List<LineItem> lineItems) {
            this.customerId = customerId;
            this.shippingPrice = shippingPrice;
            this.address = address;
            this.lineItems = lineItems;
        }

        Long getCustomerId() {
            return customerId;
        }

        BigDecimal getShippingPrice() {
            return shippingPrice;
        }

        public Address getAddress() {
            return address;
        }
    }

    class Address {

        public String streetAddress1;
        public String streetAddress2;
        private String stateName;
        private String city;
        private String zip;

        public Address(String streetAddress1, String streetAddress2, String stateName, String city, String zip) {
            this.streetAddress1 = streetAddress1;
            this.streetAddress2 = streetAddress2;
            this.stateName = stateName;
            this.city = city;
            this.zip = zip;
        }
    }

    // No discernible behavior
    // pre: The loyalty card is not expired
    // ?

    // pre: The loyalty card is expired
    // ?

    @Test
    public void createsAnOrder() {
        final long customerId = 1L;
        Customer customer = customerRepository.findById(customerId);

        Product firstProduct = productRepository.findAll().get(0);

        cartRepository.addToCart(customerId, 1L);

        cartPurchasingService.purchaseCart(customer.getId());

        List<Map<String, String>> orderData = jdbcTemplate.query("select customer_code, shipping_cost, product_id from mrt_order o " +
                "join mrt_order_line_items li on li.mrt_order_id = o.id", (RowMapper<Map<String, String>>) (rs, rowNum) -> {
            return Map.of("cust_id", rs.getString("customer_code"), "ship_c", rs.getString("shipping_cost"), "pid", rs.getString("product_id"));
        });

        assertThat(orderData).hasSize(1);
        assertThat(orderData.get(0)).containsEntry("cust_id", customer.getId().toString());
        assertThat(orderData.get(0)).containsEntry("ship_c", new BigDecimal(6.52).multiply(new BigDecimal(3.025)).add(new BigDecimal(6.95)).multiply(new BigDecimal(100)).toBigInteger().toString());
        assertThat(orderData.get(0)).containsEntry("pid", firstProduct.getId().toString());

        customer.setZip(storeRepository.first().getZip());
        customerRepository.update(customer);

        cartRepository.addToCart(customerId, 1L);
        cartPurchasingService.purchaseCart(customer.getId());

        orderData = jdbcTemplate.query("select customer_code, shipping_cost, charged_price from mrt_order o " +
                "join mrt_order_line_items li on o.id = li.mrt_order_id ", (RowMapper<Map<String, String>>) (rs, rowNum) -> {
            return Map.of("cust_id", rs.getString("customer_code"), "ship_c", rs.getString("shipping_cost"), "charged", rs.getString("charged_price"));
        });

        assertThat(orderData).hasSize(2);
        assertThat(orderData.get(1)).containsEntry("cust_id", customer.getId().toString());
        assertThat(orderData.get(1)).containsEntry("ship_c", new BigDecimal(0).toString());
        assertThat(orderData.get(1)).containsEntry("charged", new BigDecimal("9434").toString());

        customer.setZip(storeRepository.first().getZip());
        customerRepository.update(customer);
    }


}
