export const RECIEVE_PRODUCTS = "RECIEVE_PRODUCTS";
export const ADD_TO_CART = "ADD_TO_CART";
export const CANCEL_CART = "CANCEL_CART";

export default (state, action) => {
  switch (action.type) {
    case RECIEVE_PRODUCTS:
      return {
        ...state,
        products: action.payload,
      };
    case ADD_TO_CART:
      let cart = [];
      if (state.cart.some((product) => product.id === action.payload.id)) {
        cart = state.cart.map((product) => {
          if (product.id === action.payload.id) {
            product.items++;
          }
          return product;
        });
      } else {
        cart = [...state.cart, action.payload];
      }

      // Decrease items in the cart by 1
      const products = state.products.map((product) => {
        if (product.id === action.payload.id) {
          if (product.items_left > 0) product.items_left--;
        }
        return product;
      });

      return {
        ...state,
        products,
        cart,
      };

    case CANCEL_CART:
      const prods = state.products.map((prod) => {
        if (prod.id === action.payload.id) {
          prod.items_left += action.payload.items;
        }
        return prod;
      });
      return {
        ...state,
        products: prods,
        cart: state.cart.filter((product) => product.id !== action.payload.id),
      };

    default:
      return state;
  }
};
