import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import './App.css'
import Header from './assets/components/header/Header'
import CategoriesPage from './assets/components/categories/categories-page/CategoriesPage'
import CategoriesList from './assets/components/categories/categories-list/CategoriesList'
import CategoryAdd from './assets/components/categories/category-add/CategoryAdd'
import CategoryEdit from './assets/components/categories/category-edit/CategoryEdit'
import CategoryDetails from './assets/components/categories/category-details/CategoryDetails'
import Home from './assets/components/home/Home'

function App() {
  // const [count, setCount] = useState(0)

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
            <Route path="edit" element={<CategoryEdit />} />
            <Route path="2" element={<CategoryDetails />} />
          </Route>

          <Route path="/products">
            <Route path="" />
            <Route path="add" />
            <Route path="edit" />
            <Route path="details" />
          </Route>
        </Routes>
      </main>

    </Router>
    </>
  )
}

export default App
