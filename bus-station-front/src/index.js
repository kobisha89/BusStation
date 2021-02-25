import React from 'react';
import ReactDOM from 'react-dom';
import { Route, Link, HashRouter as Router, Switch } from 'react-router-dom';
import { Navbar, Nav, Container, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Home from './components/Home';
import {logout} from './services/auth';
import Login from './components/login/Login';
import Line from './components/line/Line';
import AddLine from './components/line/AddLine';
import EditLine from './components/line/EditLine';

class App extends React.Component{
    render() {
        return (
            <div>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            Home
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/lines">Lines</Nav.Link>

                            {window.localStorage['jwt'] ? 
                            <Button onClick = {()=>logout()}>Logout</Button> :
                            <Nav.Link as={Link} to="/login">Log in</Nav.Link>
                            }
                        </Nav>
                    </Navbar> 
                    <Container style={{paddingTop:"25px"}}>
                    <Switch>
                            <Route exact path="/" component={Home} />
                            <Route exact path="/lines" component={Line} />
                            <Route exact path="/lines/add" component={AddLine}/>
                            <Route exact path="/lines/edit/:id" component={EditLine} />
                            <Route exact path="/login" component={Login}/>
                    </Switch>
                    </Container>
                </Router>
            </div>
        )
    }

}

ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);