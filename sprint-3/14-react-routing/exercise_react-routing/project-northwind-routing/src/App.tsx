import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Header from './assets/components/header/Header'
import CategoriesPage from './assets/components/categories/categories-page/CategoriesPage'
import CategoriesList from './assets/components/categories/categories-list/CategoriesList'
import CategoryAdd from './assets/components/categories/category-add/CategoryAdd'
import CategoryEdit from './assets/components/categories/category-edit/CategoryEdit'
import CategoryDetails from './assets/components/categories/category-details/CategoryDetails'
import Home from './assets/components/home/Home'
import ProductSearch from './assets/components/product/product-search/ProductSearch'
import ProductAdd from './assets/components/product/product-add/ProductAdd'
import ProductEdit from './assets/components/product/product-edit/ProductEdit'
import ProductDetails from './assets/components/product/product-details/ProductDetails'
import ProductPage from './assets/components/product/product-page/ProductPage'

function App() {

  return (
    <>
    <Router>
      <Header></Header>

      <main className='container mt-4'>
        <Routes>
          <Route path="/" element={<Home />} />

          <Route path="/categories" element={<CategoriesPage />}>
            <Route path="" element={<CategoriesList />} />
            <Route path="add" element={<CategoryAdd />} />
            <Route path=":catId/edit" element={<CategoryEdit />} />
            <Route path="2" element={<CategoryDetails />} />
          </Route>

          <Route path="/products" element={<ProductPage />}>
            <Route path="" element={<ProductSearch />} />
            <Route path="add" element={<ProductAdd />} />
            <Route path=":productId/edit" element={<ProductEdit />} />
            <Route path="details" element={<ProductDetails />} />
          </Route>
        </Routes>
      </main>

    </Router>
    </>
  )
}

export default App
