package com.tidz.dream_shop.service.cart;

import org.springframework.stereotype.Service;

import com.tidz.dream_shop.exception.ResourceNotFoundException;
import com.tidz.dream_shop.model.Cart;
import com.tidz.dream_shop.model.CartItem;
import com.tidz.dream_shop.model.Product;
import com.tidz.dream_shop.repository.CartItemRepository;
import com.tidz.dream_shop.repository.CartRepository;
import com.tidz.dream_shop.service.product.IProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService {

	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	private final IProductService productService;
	private final ICartService cartService;

	@Override
	public void addItemToCart(Long cartId, Long productId, int quantity) {
		Cart cart = cartService.getCart(cartId);
		Product product = productService.getProductById(productId);
		CartItem cartItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId))
				.findFirst().orElse(null);

		if (cartItem.getId() == null) {
			cartItem.setCart(cart);
			cartItem.setProduct(product);
			cartItem.setQuantity(quantity);
			cartItem.setUnitPrice(product.getPrice());
		} else {
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
		}

		cartItem.setTotalPrice();
		cart.addItem(cartItem);
		cartItemRepository.save(cartItem);
		cartRepository.save(cart);

	}

	@Override
	public void removeItemFromCart(Long cartId, Long productId) {
		Cart cart = cartService.getCart(cartId);
		CartItem cartItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId))
				.findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found"));

		cart.removeItem(cartItem);
		cartRepository.save(cart);

	}

	@Override
	public void updateItemQuantity(Long cartId, Long productId, int quantity) {
		// TODO Auto-generated method stub

	}

}
