import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import "./App.module.scss";
import Home from "./modules/page/HomePage";
import DocumentsView from "./modules/document/DocumentsView";
import NavBar from "./modules/common/NavBar";
import {
	BrowserRouter as Router,
	Routes,
	Route,
} from "react-router-dom";
import AddDocument from "./modules/document/AddDocument";
import DocumentPofile from "./modules/document/DocumentPofile";
import AddVersionDocument from "./modules/document/AddVersionDocument";
function App() {
	return (
		<main className="container mt-5">
			<Router>
				<NavBar />
				<Routes>
					<Route
						exact
						path="/"
						element={<Home />}></Route>
					<Route
						exact
						path="/view-documents"
						element={<DocumentsView />}></Route>
					<Route
						exact
						path="/add-documents"
						element={<AddDocument />}></Route>
					<Route
						exact
						path="/AddVersionDocument/:id"
						element={<AddVersionDocument />}></Route>
					<Route
						exact
						path="/document-profile/:id"
						element={<DocumentPofile />}></Route>
				</Routes>
			</Router>
		</main>
	);
}

export default App;
