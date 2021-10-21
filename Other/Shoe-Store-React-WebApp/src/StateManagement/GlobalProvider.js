import React, { useReducer } from "react";
import GlobalContext from "./GlobalContext";
import reducer, {
  RECIEVE_PRODUCTS,
  ADD_TO_CART,
  CANCEL_CART,
} from "./GlobalReducer";

const GlobalProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);

  function recieveProducts(products) {
    dispatch({
      type: RECIEVE_PRODUCTS,
      payload: products,
    });
  }
  function addToCart(product) {
    dispatch({
      type: ADD_TO_CART,
      payload: {
        ...product,
        items: 1,
      },
    });
  }

  function cancelCartItem(product) {
    dispatch({
      type: CANCEL_CART,
      payload: product,
    });
  }
  return (
    <GlobalContext.Provider
      value={{
        products: state.products,
        cart: state.cart,
        recieveProducts,
        addToCart,
        cancelCartItem,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};

export default GlobalProvider;
