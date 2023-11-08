import { Route, Switch } from 'react-router-dom';
import './App.css';
import MainPage from './pages/MainPage';
import QnADetailPage from './pages/QnADetailPage';
import WritePage from './pages/writePage';

function App() {
  return (
    <Switch>
       <Route exact path='/' component={MainPage}></Route>
       <Route exact path='/question/add' component={WritePage}></Route>
       <Route path='/question/:Id' component={QnADetailPage}></Route>
    </Switch>
  );
}

export default App;

// import React, {useEffect, useState} from 'react';
// import axios from 'axios';
//
// function App() {
//     const [question, setQuestion] = useState([{}])
//
//     useEffect(() => {
//         axios.get('/question/1')
//             .then(response => setQuestion(response.data))
//             .catch(error => console.log(error))
//     }, []);
//
//     return (
//         <div>
//             백엔드에서 가져온 데이터입니다 : {question.content}
//         </div>
//     );
// }
//
// export default App;