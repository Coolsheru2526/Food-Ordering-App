import React from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import {
  Backdrop,
  CircularProgress,
  Divider,
  FormControl,
  FormControlLabel,
  Grid2,
  Radio,
  RadioGroup,
  Typography,
} from "@mui/material";
import MenuItemCard from "../../components/MenuItem/MenuItemCard";
import LocationOnIcon from '@mui/icons-material/LocationOn';
import TodayIcon from '@mui/icons-material/Today';

const categories = [
  "Thali",
  "Starters",
  "Indian Main Course",
  "Rice and Biryani",
  "Breads",
  "Accompaniments",
  "Dessert",
];

const foodTypes = [
  {label:"All",value:"all"},
  { label: "Vegetarian Only", value: "vegetarian" },
  { label: "Non-Vegetarian Only", value: "non_vegetarian" },
  {label:"Seasonal",value:"seasonal"},
  
];
const Restaurant = () => {
  return (
    <><div className="px-5 lg:px-20 ">
      <section>
        <h3 className="text-gray-500 py-2 mt-10">
          Home/{restaurant.restaurant?.address.country}/
          {restaurant.restaurant?.name}/{restaurant.restaurant?.id}/Order Online
        </h3>
        <div>
         
          <Grid2 container spacing={2}>
            <Grid2 item xs={12}>
            <img
            className="w-full h-[40vh] object-cover"
            src={restaurant.restaurant?.images[0]}
            alt=""
          />
            </Grid2>
            <Grid2 item size={{xs:12,lg:6}}>
            <img
            className="w-full h-[40vh] object-cover"
            src={restaurant.restaurant?.images[1]}
            alt=""
          />
            </Grid2>
            <Grid2 item size={{xs:12,lg:6}}>
            <img
            className="w-full h-[40vh] object-cover"
            src={restaurant.restaurant?.images[2]}
            alt=""
          />
            </Grid2>
          </Grid2>
        </div>
        <div className="pt-3 pb-5">
          <h1 className="text-4xl font-semibold">
            {restaurant.restaurant?.name}
          </h1>
          <p className="text-gray-500 mt-1">{restaurant.restaurant?.description}</p>
          <div className="space-y-3 mt-3">
              <p className="text-gray-500 flex items-center gap-3">
            <LocationOnIcon/> <span>{restaurant.restaurant?.address.streetAddress}
              </span> 
          </p>
          <p className="flex items-center gap-3 text-gray-500">
           <TodayIcon/> <span className=" text-orange-300"> {restaurant.restaurant?.openingHours} (Today)</span>  
          </p>
          </div>
        
        </div>
      </section>
      <Divider />

      <section className="pt-[2rem] lg:flex relative ">
        <div className="space-y-10 lg:w-[20%] filter">
          <div className="box space-y-5 lg:sticky top-28">
            
            <div className="">
              <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
                Food Type
              </Typography>
              <FormControl className="py-10 space-y-5" component="fieldset">
                <RadioGroup
                  name="food_type"
                  value={foodType || "all"}
                  onChange={handleFilter}
                >
                  {foodTypes?.map((item, index) => (
                    <FormControlLabel
                      key={index}
                      value={item.value}
                      control={<Radio />}
                      label={item.label}
                      sx={{ color: "gray" }}
                    />
                  ))}
                </RadioGroup>
                <Divider/>
                <Typography sx={{ paddingBottom: "1rem" }} variant="h5">
                Food Category
              </Typography>
                <RadioGroup
                  name="food_category"
                  value={foodCategory || "all"}
                  onChange={handleFilter}
                >
                   <FormControlLabel
                      
                      value={"all"}
                      control={<Radio />}
                      label={"All"}
                      sx={{ color: "gray" }}
                    />
                  {restaurant?.categories.map((item, index) => (
                    <FormControlLabel
                      key={index}
                      value={item.name}
                      control={<Radio />}
                      label={item.name}
                      sx={{ color: "gray" }}
                    />
                  ))}
                </RadioGroup>
              </FormControl>
            </div>
          </div>
        </div>
        <div className="lg:w-[80%] space-y-5 lg:pl-10">
          {menu?.menuItems.map((item) => (
            <MenuItemCard item={item} />
          ))}
        </div>
      </section>
    </div>
    <Backdrop
  sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
  open={menu.loading || restaurant.loading}
  
>
  <CircularProgress color="inherit" />
</Backdrop>
    </>
    
  );
};

export default Restaurant;