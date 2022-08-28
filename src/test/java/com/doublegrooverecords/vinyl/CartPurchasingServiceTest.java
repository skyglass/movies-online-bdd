package com.doublegrooverecords.vinyl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
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

  Address expectedAddress = new Address("1120 N Ashland Ave", "", "Chicago", "Illinois", "60622");

  @Test
  public void orderCreatedFromCart() {
    cartRepository.addToCart(customerId, productId);
    cartRepository.addToCart(customerId, secondProductId);

    cartPurchasingService.purchaseCart(customerId);

    List<Order> allOrders = findAllOrders(customerId);

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

  private List<Order> findAllOrders(long customerCode) {
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
            "join mrt_order_line_items li on li.mrt_order_id = o.id " +
            "where customer_code = ? ", (RowMapper<Order>) (rs, rowNum) -> {
      Long orderId = rs.getLong("id");

      if (orders.containsKey(orderId)) {
        Order order = orders.get(orderId);
        order.lineItems.add(new LineItem(rs.getLong("product_id"), rs.getBigDecimal("charged_price")));
      } else {
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(rs.getLong("product_id"), rs.getBigDecimal("charged_price")));
        Order order = new Order(
                rs.getLong("id"), rs.getLong("customer_code"),
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
    }, customerCode);

    return orders
            .values()
            .stream()
            .sorted(Comparator.comparing(Order::getId))
            .collect(Collectors.toList());
  }

  @Test
  public void theShippingPriceIsUnchanged_whenThereAreNoStoresNearTheCustomer() {
    cartRepository.addToCart(customerId, productId);

    cartPurchasingService.purchaseCart(customerId);

    List<Order> allOrders = findAllOrders(customerId);

    Order order = allOrders.get(0);
    assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("2667"));
  }

  @Test
  public void theShippingIsFree_whenThereAreManyStoresNearby() {
    final Long customerNearTwoStores = 3L;

    cartRepository.addToCart(customerNearTwoStores, productId);
    cartPurchasingService.purchaseCart(customerNearTwoStores);

    List<Order> allOrders = findAllOrders(customerNearTwoStores);
    assertThat(allOrders).hasSize(1);
    assertThat(allOrders.get(0).getShippingPrice()).isEqualTo(new BigDecimal("0"));
  }

  @Test
  public void theShippingPriceIsFree_whenThereIsOneStoreNearTheCustomer() {
    final Long customerNearOneStore = 4L;
    Customer customer = customerRepository.findById(customerNearOneStore);

    cartRepository.addToCart(customerNearOneStore, productId);
    cartPurchasingService.purchaseCart(customer.getId());

    List<Order> allOrders = findAllOrders(customerNearOneStore);

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

    List<Order> allOrders = findAllOrders(customerId);
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

    List<Order> allOrders = findAllOrders(customerId);

    Order order = allOrders.get(10);
    assertThat(order.getCustomerId()).isEqualTo(customerId);
    assertThat(order.getShippingPrice()).isEqualTo(new BigDecimal("2667").toString());
    assertThat(order.lineItems.get(0).getChargedPrice()).isEqualTo(new BigDecimal(0).toString());
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

    List<Order> allOrders = findAllOrders(customerId);
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
    Long id;
    Long customerId;
    BigDecimal shippingPrice;
    private final Address address;
    List<LineItem> lineItems;

    public Order(Long id, Long customerId, BigDecimal shippingPrice, Address address, List<LineItem> lineItems) {
      this.id = id;
      this.customerId = customerId;
      this.shippingPrice = shippingPrice;
      this.address = address;
      this.lineItems = lineItems;
    }

    public Long getId() {
      return id;
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
    private final String stateName;
    private final String city;
    private final String zip;

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
}
