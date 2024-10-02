import { Outlet } from "react-router-dom";

export default function ProductPage()
{
    return (
        <>
        <h1>Products</h1>

        <Outlet />
        </>
    )
}