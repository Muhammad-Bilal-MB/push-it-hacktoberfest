import React, { useContext } from "react";
import Navbar from "./Navbar";
import { Grid, Typography, Container } from "@material-ui/core";
// import OrderSummary from "../components/Cart/OrderSummary";
import { makeStyles } from "@material-ui/core/styles";
import GlobalContext from "../StateManagement/GlobalContext";
import CartItemList from "./cartItemList";

const useStyles = makeStyles((theme) => ({
  root: {
    width: "100wh",
    flexGrow: 10,
    //   background: "linear-gradient(90deg, rgba(253,187,45,1) 20%, rgba(34,193,195,1) 100%)",
  },
  heading: {
    fontWeight: "bold",
    marginTop: theme.spacing(4),
  },

  total: {
    marginBottom: theme.spacing(4),
  },
}));

const Cart = () => {
  const classes = useStyles();
  const { cart } = useContext(GlobalContext);

  const items = cart.map((p) => p.items);
  const prices = cart.map((p) => p.price * p.items);

  let numOfItems = 0;
  let totalPrice = 0;
  if (items.length) numOfItems = items.reduce((a, b) => a + b);
  if (prices.length) totalPrice = prices.reduce((a, b) => a + b);

  return (
    <div className={classes.root}>
      <Navbar />
      <Container>
        <Typography variant="h4" component="h4" className={classes.heading}>
          Amount to Pay: <strong>${totalPrice}</strong>
        </Typography>
        <Typography variant="button" component="p" className={classes.total}>
          <strong>{numOfItems}</strong> items in Your cart
        </Typography>
        <Grid container spacing={4}>
          <Grid item xs={12} md={8}>
            <CartItemList />
          </Grid>
          {/* <Grid item xs={12} md={4}>
              <OrderSummary />
            </Grid> */}
        </Grid>
      </Container>
    </div>
  );
};

export default Cart;
