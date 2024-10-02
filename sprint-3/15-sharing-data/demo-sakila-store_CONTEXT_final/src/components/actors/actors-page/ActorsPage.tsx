import { Outlet } from "react-router-dom";
// import ActorsList from "../actors-list/ActorsList";
// import ActorDetails from "../actor-details/ActorDetails";
// import ActorAdd from "../actor-add/ActorAdd";
import ActorsContextProvider from "../../../contexts/ActorsContextProvider";

export default function ActorsPage() {
    return (

        <ActorsContextProvider>
           
            <h1>Actors</h1>

            <Outlet />

        </ActorsContextProvider>
    )
}