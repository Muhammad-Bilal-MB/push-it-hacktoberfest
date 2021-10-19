import React, { useContext } from "react";
import CartItemCard from "./cartItemCard";
import GlobalContext from "../StateManagement/GlobalContext";
import { Typography } from "@material-ui/core";

const CartItemList = () => {
  const { cart } = useContext(GlobalContext);

  return (
    <>
      {cart.length > 0 ? (
        Object.entries(cart).map(([productID, { image, title, _id }]) => (
          <CartItemCard key={productID} productID={productID} />
        ))
      ) : (
        <div style={{ width: "100%" }}>
          <Typography
            color="error"
            variant="body1"
            style={{ fontSize: "20px", textAlign: "center" }}
          >
            Your Cart is Empty
          </Typography>
        </div>
      )}
    </>
  );
};

//   return (
//     <>
//       {cart.length > 0 ? (
//         Object.entries(products).map(([productID, { image, title, _id }]) => (
//           <CartItemCard key={productID} productID={productID} />
//         ))
//       ) : (
//         <div style={{ width: "100%" }}>
//           <Typography
//             color="error"
//             variant="body1"
//             style={{ color: "#fff", fontSize: "20px", textAlign: "center" }}
//           >
//             Your Cart is Empty
//           </Typography>
//         </div>
//       )}
//     </>
//   );
// };

export default CartItemList;
