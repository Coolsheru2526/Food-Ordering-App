import { CssBaseline, ThemeProvider } from "@mui/material"
import darkTheme from "./Theme/DarkTheme"
import Navbar from "./component/navbar/Navbar"
import Home from "./component/Home/Home"

function App() {

  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline/>
      <Navbar/>
      <Home />
    </ThemeProvider>  
    )
}

export default App
