import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Header from './components/header/Header'
import CategoriesPage from './components/categories/categories-page/CategoriesPage'
import CategoriesList from './components/categories/categories-list/CategoriesList'
import CategoryAdd from './components/categories/category-add/CategoryAdd'
import CategoryEdit from './components/categories/category-edit/CategoryEdit'
import CategoryDetails from './components/categories/category-details/CategoryDetails'
import Home from './components/home/Home'
import ProductSearch from './components/product/product-search/ProductSearch'
import ProductAdd from './components/product/product-add/ProductAdd'
import ProductEdit from './components/product/product-edit/ProductEdit'
import ProductDetails from './components/product/product-details/ProductDetails'
import ProductPage from './components/product/product-page/ProductPage'

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
            <Route path=":catId" element={<CategoryDetails />} />
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
