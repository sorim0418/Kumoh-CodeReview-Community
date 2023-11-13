import { Route, Switch } from 'react-router-dom';
import './App.css';
import MainPage from './pages/MainPage';
import QnADetailPage from './pages/QnADetailPage';
import WritePage from './pages/writePage';
import Header from './components/Header';

function App() {
  return (
      <>
        <Header/>
        <Switch>
          <Route exact path='/question' component={MainPage}></Route>
          <Route exact path='/question/add' component={WritePage}></Route>
          <Route path='/question/:Id' component={QnADetailPage}></Route>
        </Switch>
      </>
  );
}

export default App;