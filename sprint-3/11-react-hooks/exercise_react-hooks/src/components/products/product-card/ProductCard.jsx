import { XCircleFill } from "react-bootstrap-icons"

export default function ProductCard({product, id})
{
    return (
        <div className="card category-card">
            <div id="category-header" className="card-header">{product}</div>
            <div id="category-body" className="card-body">

            </div>
            <div className="card-footer"><XCircleFill color="red"></XCircleFill></div>
        </div>
    )
}