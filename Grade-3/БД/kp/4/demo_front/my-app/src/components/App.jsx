import React from 'react';
import {Route,Routes} from 'react-router-dom';
import LoginPage from "./login/login";
import SignUp from "./register/register";
import {Main} from "./main/main"
class App extends React.Component {
    render(){
        return(
            <div>
                <Routes>
                    <Route path="/" element={
                            <LoginPage/>
                    }/>
                    <Route path="/signup" element={<SignUp/>}/>
                    <Route path="/main" element={<Main/>}/>
                </Routes>
            </div>
        )
    }
}
export default App;