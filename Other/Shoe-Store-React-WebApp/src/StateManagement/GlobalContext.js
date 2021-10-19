import { createContext } from "react";

export const initialState = {
  prod: [],
  cart: [],
};

const GlobalContext = createContext(initialState);

export default GlobalContext;
