import React, { useContext } from "react";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import { makeStyles } from "@material-ui/core/styles";
import CancelIcon from "@material-ui/icons/Cancel";
import IconButton from "@material-ui/core/IconButton";
import GlobalContext from "../StateManagement/GlobalContext";

import Shoesdata from "../ProductsData/Shoes.json";

const useStyles = makeStyles((theme) => ({
  root: {
    minHeight: 200,
    width: "100%",
    borderBottom: `1px solid ${theme.palette.text.secondary}`,
    position: "relative",
    marginBottom: "20px",
    paddingBottom: "20px",

    [theme.breakpoints.only("xs")]: {
      minHeight: 150,
    },
  },
  media: {
    width: "100%",
    height: "100%",
    boxShadow: "6px 6px 10px 2px rgba(0, 0, 0, 0.2)",
    border: "4px solid #fff",
    borderRadius: "32% 68% 76% 24% / 65% 66% 34% 35%",
    transition: "1s ease",

    "&:hover": {
      cursor: "pointer",
      boxShadow: "none",
      border: "none",
      borderRadius: "50%",
      transition: "1s ease",
    },

    [theme.breakpoints.only("xs")]: {},
  },
  content: {
    padding: theme.spacing(2),
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-between",
  },
  bottomRow: {
    display: "flex",
    justifyContent: "space-between",

    [theme.breakpoints.only("xs")]: {
      color: "black",
    },
  },

  responsiveText: {
    fontSize: "1.5rem",

    [theme.breakpoints.down("xs")]: {
      fontSize: "1rem",
    },
  },

  blackOnXS: {
    [theme.breakpoints.down("xs")]: {
      //color: theme.palette.text.primary,
      fontWeight: "bolder",
    },
  },

  cancelBtn: {
    position: "absolute",
    fontSize: "3rem",
    left: 2,
    top: 2,

    [theme.breakpoints.down("xs")]: {
      fontSize: "1.6rem",
      right: 2,
    },
  },
  name: {
    fontSize: "2.5rem",

    [theme.breakpoints.down("xs")]: {
      fontSize: "1.6rem",
    },
  },
}));

const CartItemCard = ({ productID }) => {
  const product = Shoesdata[productID];
  console.log(productID);
  const classes = useStyles();

  const { cancelCartItem } = useContext(GlobalContext);

  function handleCancelBtn() {
    console.log("cancel", product.title);
    cancelCartItem(product);
  }
  return (
    <Grid container className={classes.root}>
      <IconButton
        //color="inherit"
        className={classes.cancelBtn}
        onClick={handleCancelBtn}
      >
        <CancelIcon className={classes.cancelBtn} />
      </IconButton>
      <Grid item xs={4}>
        <img
          className={classes.media}
          src={product.image}
          alt={product.title}
        />
      </Grid>

      <Grid item xs={8} className={classes.content}>
        <div>
          <Typography
            variant="h6"
            component="h3"
            color="textSecondary"
            className={classes.responsiveText}
          >
            {product.title}
          </Typography>
        </div>
        <div className={classes.bottomRow}>
          <Typography
            variant="button"
            color="textSecondary"
            className={(classes.responsiveText, classes.blackOnXS)}
          >
            ${product.price}
          </Typography>
        </div>
      </Grid>
    </Grid>
  );
};
export default CartItemCard;
