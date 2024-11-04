import React from "react";
import ShoppingBagIcon from "@mui/icons-material/ShoppingBag";
import { Divider, Drawer, useMediaQuery } from "@mui/material";
import LogoutIcon from "@mui/icons-material/Logout";
import FavoriteIcon from "@mui/icons-material/Favorite";
import HomeIcon from "@mui/icons-material/Home";
import AccountBalanceWalletIcon from "@mui/icons-material/AccountBalanceWallet";
import NotificationsIcon from "@mui/icons-material/Notifications";
import EventIcon from "@mui/icons-material/Event";
import { useNavigate } from "react-router-dom";

const menu = [
  { title: "Orders", icon: <ShoppingBagIcon /> },
  { title: "Favorites", icon: <FavoriteIcon /> },
  { title: "Address", icon: <HomeIcon /> },
  { title: "Payments", icon: <AccountBalanceWalletIcon /> },
  { title: "Notification", icon: <NotificationsIcon /> },
  { title: "Events", icon: <EventIcon /> },
  { title: "Logout", icon: <LogoutIcon /> },
];

const ProfileNavigation = ({ handleClose, open }) => {
  const isSmallScreen = useMediaQuery("(max-width:1080px)");

  const navigate = useNavigate();

  const handleNavigate = (item) => {
    navigate(`/my-profile/${item.title.toLowerCase()}`);
  }

  return (
    <React.Fragment>
      <Drawer
        sx={{ zIndex: 1 }}
        anchor={"left"}
        open={open}
        onClose={handleClose}
        variant={isSmallScreen ? "temporary" : "permanent"}
      >
        <div className="w-[50vw] lg:w-[20vw] h-[100vh] flex flex-col justify-center text-xl space-y-8 pt-16">
          {menu.map((item, i) => (
            <>
              <div
                className="px-5 flex items-center space-x-5 cursor-pointer"
                onClick={() => handleNavigate(item)}
              >
                {item.icon}
                <span>{item.title}</span>
              </div>
              {i !== menu.length - 1 && <Divider />}
            </>
          ))}
        </div>
      </Drawer>
    </React.Fragment>
  );
};

export default ProfileNavigation;
