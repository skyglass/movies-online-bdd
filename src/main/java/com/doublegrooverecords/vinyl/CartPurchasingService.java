package com.doublegrooverecords.vinyl;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class CartPurchasingService {
    private CartRepository cartRepository;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private CouponDiscount couponDiscount;
    private StoreNearbyDiscount storeNearbyDiscount;
    private LoyaltyCardDiscount loyaltyCardDiscount;

    public CartPurchasingService(CartRepository cartRepository, OrderRepository orderRepository, LoyaltyCardRepository loyaltyCardRepository, CustomerRepository customerRepository, CouponDiscount couponDiscount, StoreNearbyDiscount storeNearbyDiscount, LoyaltyCardDiscount loyaltyCardDiscount) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.couponDiscount = couponDiscount;
        this.storeNearbyDiscount = storeNearbyDiscount;
        this.loyaltyCardDiscount = loyaltyCardDiscount;
    }

    public void purchaseCart(Long customerId) {
        purchaseCart(customerId, Calendar.getInstance());
    }

    public void purchaseCart(Long customerId, Calendar calendar) {
        Cart cart = cartRepository.find(customerId);
        Customer customer = customerRepository.findById(customerId);
        List<Product> list = makeProducts(cart);

        Order order = makeOrder(customerId, customer, list);

        storeNearbyDiscount.apply(customer, order);
        loyaltyCardDiscount.apply(customerId, calendar, list);
        couponDiscount.apply(cart, order);

        orderRepository.create(order);
        cartRepository.clear(customerId);
    }

    private List<Product> makeProducts(Cart cart) {
        List<Product> list = new ArrayList<>();
        for (CartProduct p : cart.getProducts()) {
            for (int i = 0; i < p.getQuantity(); i++)
                list.add(new Product(p.getId(), p.getAlbumTitle(), p.getArtists().get(0), p.getImageUrl(), p.getIndividualPrice(), null, null, null));
        }
        return list;
    }

    private Order makeOrder(Long customerId, Customer customer, List<Product> list) {
        Order order = new Order();
        order.setCustomerId(customerId);

        order.setStreetAddress1(customer.getStreetAddress1());
        order.setStreetAddress2(customer.getStreetAddress2());
        order.setStateName(customer.getStateName());
        BigDecimal shippingCost = new BigDecimal(6.52).multiply(new BigDecimal(3.025));
        order.setShippingCost(shippingCost);
        order.setCity(customer.getCity());
        order.setZip(customer.getZip());
        order.setShippingCost(shippingCost.add(new BigDecimal(6.95)));
        order.setProducts(list);
        return order;
    }
}
