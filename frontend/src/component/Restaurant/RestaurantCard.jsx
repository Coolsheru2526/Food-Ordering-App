import * as React from "react";
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import DeleteIcon from "@mui/icons-material/Delete";
import EditIcon from "@mui/icons-material/Edit";


export default function RestaurantCard({ item }) {
  return (
    <Card sx={{ maxWidth: 345, m: "1rem" }}>
      <CardHeader
        avatar={
          <Avatar
            sx={{ bgcolor: "#e91e63", color: "white" }}
            aria-label="recipe"
          >
            Z
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={item.name}
        subheader="September 14, 2016"
      />
      <img className="h-[17rem] w-full object-cover" src={item.imageUrl} alt="" />
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          This impressive paella is a perfect party dish and a fun meal to cook
          together with your guests. Add 1 cup of frozen peas along with the
          mussels, if you like.
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <div className="flex w-full items-center justify-between">
          <div>
            <IconButton aria-label="delete">
              <DeleteIcon />
            </IconButton>
          </div>
          <div>
            <Button color={item.open?"warning":"success"}>
              {item.open?"Close":"Open"}
            </Button>
          </div>
          <div>
            <Button size="small">
              Dashboard
            </Button>
          </div>
        </div>
      </CardActions>
    </Card>
  );
}
