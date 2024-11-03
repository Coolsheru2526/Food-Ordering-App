import {
    Accordion,
    AccordionDetails,
    AccordionSummary,
    Button,
    Checkbox,
    FormControlLabel,
    FormGroup,
    Typography,
  } from "@mui/material";
  import React from "react";
  import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
  
  const MenuItemCard = ({ item }) => {
  
    return (
      <>
        <Accordion>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1a-content"
            id="panel1a-header"
          >
            <div className="lg:flex items-center justify-between">
              <div className="lg:flex items-center lg:space-x-5">
                <img
                  className="w-[7rem] h-[7rem] object-cover"
                  src={item.images[0]}
                  alt=""
                />
  
                <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
                  <p className="font-semibold text-xl">{item.name}</p>
                  <p>₹{item.price}</p>
                  <p className="text-gray-400">{item.description}</p>
                </div>
              </div>

            </div>
          </AccordionSummary>
          <AccordionDetails>
            <form  >
              <div className="flex gap-5 flex-wrap">
                 {Object.keys(
                            categorizedIngredients(item?.ingredients)
                          )?.map((category) => (
                <div className="pr-5">
                  
                  <p>{category}</p>
                  <FormGroup >
                    {categorizedIngredients(item?.ingredients)[
                                  category
                                ].map((ingredient, index) => (
                      <FormControlLabel
                        key={ingredient.name}
                        control={
                          <Checkbox
                            checked={selectedIngredients.includes(
                              ingredient.name
                            )}
                            disabled={!ingredient.inStock}
                          />
                        }
                        label={ingredient.name}
                      />
                    ))}
                  </FormGroup>
                </div>
              ))}
              </div>
             
  
              <div className="pt-5">
                <Button variant="contained" disabled={!item.available} type="submit">
                  {item.available?"Add To Cart":"Out of stock"}
                </Button>
              </div>
            </form>
          </AccordionDetails>
        </Accordion>
      </>
    );
  };
  
  export default MenuItemCard;
  