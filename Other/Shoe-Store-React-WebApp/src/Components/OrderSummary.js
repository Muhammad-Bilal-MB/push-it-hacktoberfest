import React, { useContext } from "react";
import Grid from "@material-ui/core/Grid";
import { Typography, Button } from "@material-ui/core";
import { makeStyles } from "@material-ui/core/styles";
import GlobalContext from "../StateManagement/GlobalContext";

const useStyles = makeStyles((theme) => ({
  root: {
    border: `1px solid ${theme.palette.grey[400]}`,
    padding: theme.spacing(2),
    backgroundColor: theme.palette.background.paper,
    borderRadius: "20px",
  },
  heading: {
    fontWeight: "bold",
    marginBottom: theme.spacing(3),
  },
  row: {
    display: "flex",
    justifyContent: "space-between",
    marginBottom: theme.spacing(1),
  },

  checkoutBtn: {
    marginTop: theme.spacing(3),
    marginBottom: theme.spacing(3),
  },
  button: {
    marginTop: theme.spacing(5),
    background:
      "linear-gradient(-90deg, rgba(253,187,45,1) 20%, rgba(34,193,195,1) 100%)",
    boxShadow: "6px 6px 10px 2px rgba(0, 0, 0, 0.2)",
    textShadow: "1px 1px 1px #fff",
    transition: "1s ease",

    "&:hover": {
      top: "2px",
      boxShadow: "none",
      color: "#fff",
      textShadow: "none",
      transition: "1s ease",
    },
  },
  free: {
    color: "#ff0000",
  },
}));

const OrderSummary = () => {
  const classes = useStyles();
  const { cart } = useContext(GlobalContext);

  const items = cart.map((p) => p.items);
  const prices = cart.map((p) => p.price * p.items);

  let numOfItems = 0;
  let totalPrice = 0;
  if (items.length) numOfItems = items.reduce((a, b) => a + b);
  if (prices.length) totalPrice = prices.reduce((a, b) => a + b);

  function handleCheckout() {
    return <p>Thank your For Shopping</p>;
  }

  return (
    <>
      <Grid container className={classes.root}>
        <Grid item xs={12}>
          <Typography variant="h5" className={classes.heading}>
            Order Summary <br />
            <Typography variant="button" className={classes.free}>
              Free Delivery
            </Typography>
          </Typography>
        </Grid>
        <Grid item xs={12} className={classes.row}>
          <Typography variant="button">{numOfItems} Items</Typography>
          <Typography variant="button">${totalPrice}</Typography>
        </Grid>
        <Grid item xs={12} className={classes.row}>
          <Typography variant="button">Total</Typography>
          <Typography variant="button">${totalPrice}</Typography>
        </Grid>
      </Grid>
      <Button className={classes.button} fullWidth onClick={handleCheckout}>
        Checkout
      </Button>
    </>
  );
};

export default OrderSummary;
