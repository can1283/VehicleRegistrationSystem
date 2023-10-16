import {createBrowserRouter} from "react-router-dom";
import Dashboard from "../pages/dashboard";
import Register from "../pages/register";
import Login from "../pages/login";
import Home from "../pages/home";
import AddVehicleForm from "../pages/addVehicleForm";
import ChangePass from "../components/change-pass";
import EditVehicle from "../components/edit-vehicle";


export const router = createBrowserRouter([
    {
        path: "/",
        element: <Home/>,
    },
    {
        path: "/dashboard",
        element: <Dashboard/>,
    },
    {
        path: "/register",
        element: <Register/>,
    },
    {
        path: "/login",
        element: <Login/>,
    },
    {
        path: "/add",
        element: <AddVehicleForm/>,
    },
    {
        path: "/password",
        element: <ChangePass/>,
    },
    {
        path: "/edit/:vehicleId",
        element: <EditVehicle/>,
    }
]);