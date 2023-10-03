/*// Import the necessary modules
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import LoginForm from "./components/Login"
import axios from "axios";
import { BrowserRouter, Link, useNavigate } from "react-router-dom";
// Mock the axios module
jest.mock("axios");

// Define a test suite for the LoginForm component
describe("LoginForm", () => {
  // Define a test case for rendering the component
  test("renders the component", () => {
    // Render the component
    <BrowserRouter>
    render(<LoginForm />);
    </BrowserRouter>
    // Expect to see the elements in the component
    expect(screen.getByText("Sign In")).toBeInTheDocument();
    expect(screen.getByLabelText("Email")).toBeInTheDocument();
    expect(screen.getByLabelText("Password")).toBeInTheDocument();
    expect(screen.getByRole("button")).toBeInTheDocument();
  });

  // Define a test case for validating the email input
  test("validates the email input", async () => {
    // Render the component
    render(<LoginForm />);

    // Get the email input element
    const emailInput = screen.getByLabelText("Email");

    // Type an invalid email
    userEvent.type(emailInput, "invalid");

    // Expect to see an error message
    expect(await screen.findByText("Email is not valid")).toBeInTheDocument();

    // Clear the input
    userEvent.clear(emailInput);

    // Type a valid email
    userEvent.type(emailInput, "valid@example.com");

    // Expect to not see an error message
    expect(screen.queryByText("Email is not valid")).not.toBeInTheDocument();
  });

  // Define a test case for validating the password input
  test("validates the password input", async () => {
    // Render the component
    render(<LoginForm />);

    // Get the password input element
    const passwordInput = screen.getByLabelText("Password");

    // Type an invalid password
    userEvent.type(passwordInput, "short");

    // Expect to see an error message
    expect(
      await screen.findByText(
        "Password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character"
      )
    ).toBeInTheDocument();

    // Clear the input
    userEvent.clear(passwordInput);

    // Type a valid password
    userEvent.type(passwordInput, "Strong@123");

    // Expect to not see an error message
    expect(
      screen.queryByText(
        "Password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character"
      )
    ).not.toBeInTheDocument();
  });

  // Define a test case for submitting the form with valid inputs
  test("submits the form with valid inputs", async () => {
    // Render the component
    render(<LoginForm />);

    // Get the input elements
    const emailInput = screen.getByLabelText("Email");
    const passwordInput = screen.getByLabelText("Password");
    const submitButton = screen.getByRole("button");

    // Type valid inputs
    userEvent.type(emailInput, "valid@example.com");
    userEvent.type(passwordInput, "Strong@123");

    // Mock the axios post method to return a token
    axios.post.mockResolvedValue({ data: "token" });

    // Click the submit button
    userEvent.click(submitButton);

    // Expect the axios post method to be called with the correct arguments
    expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/auth/login", {
      email: "valid@example.com",
      password: "Strong@123",
    });

    // Expect the localStorage to be set with the token
    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith("token", "token");
    });
  });

  // Define a test case for submitting the form with invalid inputs
  test("submits the form with invalid inputs", async () => {
    // Render the component
    <BrowserRouter>
    render(<LoginForm />);
    </BrowserRouter>
    // Get the input elements
    const emailInput = screen.getByLabelText("email");
    const passwordInput = screen.getByLabelText("password");
    const submitButton = screen.getByRole("button");

    // Type invalid inputs
    userEvent.type(emailInput, "invalid");
    userEvent.type(passwordInput, "short");

    // Click the submit button
    userEvent.click(submitButton);

    // Expect to see an alert message
    await waitFor(() => {
      expect(window.alert).toHaveBeenCalledWith(
        "Please fix the form errors before submitting."
      );
    });

    // Expect the axios post method to not be called
    expect(axios.post).not.toHaveBeenCalled();
  });
/*
  // Define a test case for handling server errors
  test("handles server errors", async () => {
    // Render the component
    <BrowserRouter>
    render(<LoginForm />);
    </BrowserRouter>
    // Get the input elements
    const emailInput = screen.getByLabelText("Email");
    const passwordInput = screen.getByLabelText("Password");
    const submitButton = screen.getByRole("button");

    // Type valid inputs
    userEvent.type(emailInput, "valid@example.com");
    userEvent.type(passwordInput, "Strong@123");

    // Mock the axios post method to return an error
    axios.post.mockRejectedValue({ response: { data: "Server error" } });

    // Click the submit button
    userEvent.click(submitButton);

    // Expect to see an error message
    expect(await screen.findByText("Server error")).toBeInTheDocument();
  });
});
*/
/*
import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import LoginForm from "./components/Login"
import axios from 'axios';
import { BrowserRouter, useNavigate } from 'react-router-dom';
// Define a mock function for axios.post
jest.mock("axios", () => ({
  post: jest.fn(() => Promise.resolve({ data: {} })),
}));

// Define a mock function for useNavigate
jest.mock("react-router-dom", () => ({
  useNavigate: jest.fn(() => jest.fn()),
}));

// Define some test data
const validFormData = {
  email: "test@user.com",
  password: "Test@123",
  
};
const invalidFormData = {

  
  email: "testuser",
  password: "test",
  
};

// Define a helper function to fill the form inputs
const fillForm = (formData) => {
  userEvent.type(screen.getByLabelText(/email/i), formData.email);
  userEvent.type(screen.getByLabelText(/password/i), formData.password);

};

// Define the test cases
describe("LoginForm", () => {
  test("renders the form with the required inputs and buttons", () => {
    // Render the component
   
    render(<LoginForm/>);
   

    // Check if the inputs and buttons are present
    
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
  });
});
*/

/*

// Import React Testing Library and other dependencies
import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import axios from 'axios';
import LoginForm from "./components/Login"
import { MemoryRouter } from "react-router-dom";



// Mock the axios.post method to simulate the API call
jest.mock('axios');
axios.post.mockResolvedValue({ data: 'some-token' });

// Define a test suite for the login component
describe('Login component', () => {
  // Define a test case for rendering the login form
  test('renders the login form', () => {
    // Render the login component
    <MemoryRouter>
    render(<LoginForm />);
    </MemoryRouter>
    // Assert that the email and password inputs are present
    expect(screen.getByRole('textbox', { name: /email/i })).toBeInTheDocument();
    expect(screen.getByLabelText(/Password/i)).toBeInTheDocument();

    // Assert that the login button is present and disabled by default
    
    expect(screen.getByRole('button', { name: /sign in/i })).toBeDisabled();
  });

  // Define a test case for submitting the login form with valid inputs
  test('submits the login form with valid inputs', async () => {
    // Render the login component
    <MemoryRouter>
    render(<LoginForm />);
    </MemoryRouter>
    // Type the email and password into the inputs
    userEvent.type(screen.getByLabelText(/username/i), 'root@email.com');
    userEvent.type(screen.getByLabelText(/password/i), 'pass1234');

    // Assert that the login button is enabled
    expect(screen.getByRole('button', { name: /sign in/i })).toBeEnabled();

    // Click the login button
    userEvent.click(screen.getByRole('button', { name: /sign in/i }));

    // Assert that the axios.post method is called with the correct arguments
    expect(axios.post).toHaveBeenCalledWith('http://localhost:8080/auth/login', {
      email: 'root@email.com',
      password: 'pass1234',
    });

    // Wait for the API call to resolve
    await waitFor(() => {
      // Assert that the token is stored in the local storage
      expect(localStorage.getItem('token')).toBe('some-token');

      // Assert that the notification is stored in the local storage
      //expect(localStorage.getItem('notification')).toBe('true');

      // Assert that the user is redirected to the products page
      //expect(window.location.pathname).toBe('/products');
    });
  });

  // Define a test case for submitting the login form with invalid inputs
  test('submits the login form with invalid inputs', async () => {
    // Render the login component
    <MemoryRouter>
    render(<LoginForm />);
    </MemoryRouter>
    // Type the email and password into the inputs
    userEvent.type(screen.getByLabelText(/username/i), 'invalid-email');
    userEvent.type(screen.getByLabelText(/password/i), 'short');

    // Assert that the login button is enabled
    expect(screen.getByRole('button', { name: /sign in/i })).toBeEnabled();

    // Click the login button
    userEvent.click(screen.getByRole('button', { name: /sign in/i }));

    // Assert that the axios.post method is not called
    expect(axios.post).not.toHaveBeenCalled();

    // Wait for the error messages to appear
    await waitFor(() => {
      // Assert that the email input shows an error message
      expect(screen.getByText(/email is not valid/i)).toBeInTheDocument();
    })
      // Assert that the password input shows an error message
      await waitFor(() => {
      expect(
        screen.getByText(
          /password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character/i
        )
      ).toBeInTheDocument();
    });
  });
});
*/
/*

// Import the libraries and the component to test
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { createMemoryHistory } from "history";
import { Router } from "react-router-dom";
import axios from "axios";
import LoginForm from "./components/Login";
import Enzyme, { shallow } from "enzyme";
//import Adapter from "@wojtekmaj/enzyme-adapter-react-17";
import Adapter from "@cfaester/enzyme-adapter-react-18"

// Configure Enzyme to work with React 17
Enzyme.configure({ adapter: new Adapter() });

// Mock the axios module to simulate the server response
jest.mock("axios");

// Define some mock data for the tests
const mockFormData = {
  email: "test@test.com",
  password: "Test@1234",
};

const mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZW1haWwiOiJ0ZXN0QHRlc3QuY29tIiwiaWF0IjoxNjM1MjYyMjE0fQ.0kx1lZ8w2n6Z8L4fFw0f0q3g7r0y9Q8Zy7Z7ZDm7Z0k";

const mockError = "Invalid email or password";

// Define a custom render function to wrap the component with a Router
const renderWithRouter = (ui, { route = "/" } = {}) => {
  const history = createMemoryHistory({ initialEntries: [route] });
  const Wrapper = ({ children }) => (
    <Router history={history}>{children}</Router>
  );
  return {
    ...render(ui, { wrapper: Wrapper }),
    history,
  };
};

// Test that the component renders correctly and matches the snapshot
test("renders LoginForm component", () => {
  const wrapper = shallow(<LoginForm />);
  expect(wrapper).toMatchSnapshot();
});

// Test that the component displays the logo, the title, the description, and the email image
test("displays the logo, the title, the description, and the email image", () => {
  renderWithRouter(<LoginForm />);
  expect(screen.getByAltText("There is a notification image")).toBeInTheDocument();
  expect(screen.getByText("e-MAIL NOTIFY")).toBeInTheDocument();
  expect(screen.getByText("It allows you to quickly know the status of emails")).toBeInTheDocument();
  expect(screen.getByAltText("There is an image")).toBeInTheDocument();
});

// Test that the component has two input fields for email and password, and a submit button
test("has two input fields for email and password, and a submit button", () => {
  renderWithRouter(<LoginForm />);
  expect(screen.getByLabelText("Email")).toBeInTheDocument();
  expect(screen.getByLabelText("Password")).toBeInTheDocument();
  expect(screen.getByRole("button", { name: "Sign In" })).toBeInTheDocument();
});

// Test that the component validates the email and password inputs and shows error messages if they are invalid
test("validates the email and password inputs and shows error messages if they are invalid", async () => {
  renderWithRouter(<LoginForm />);
  const emailInput = screen.getByLabelText("Email");
  const passwordInput = screen.getByLabelText("Password");
  const submitButton = screen.getByRole("button", { name: "Sign In" });

  // Enter an invalid email and a valid password
  fireEvent.change(emailInput, { target: { value: "test" } });
  fireEvent.change(passwordInput, { target: { value: "Test@1234" } });

  // Click the submit button
  fireEvent.click(submitButton);

  // Expect an error message for the email input
  await waitFor(() => {
    expect(screen.getByText("Email is not valid")).toBeInTheDocument();
  });

  // Enter a valid email and an invalid password
  fireEvent.change(emailInput, { target: { value: "test@test.com" } });
  fireEvent.change(passwordInput, { target: { value: "test" } });

  // Click the submit button
  fireEvent.click(submitButton);

  // Expect an error message for the password input
  await waitFor(() => {
    expect(
      screen.getByText(
        "Password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character"
      )
    ).toBeInTheDocument();
  });
});

// Test that the component calls the handleChange function when the user types in the input fields
test("calls the handleChange function when the user types in the input fields", () => {
  const wrapper = shallow(<LoginForm />);
  const handleChange = jest.spyOn(wrapper.instance(), "handleChange");

  // Simulate typing in the email input
  wrapper.find("#email").simulate("change", {
    target: { name: "email", value: "test@test.com" },
  });

  // Expect the handleChange function to be called
  expect(handleChange).toHaveBeenCalled();

  // Simulate typing in the password input
  wrapper.find("#password").simulate("change", {
    target: { name: "password", value: "Test@1234" },
  });

  // Expect the handleChange function to be called
  expect(handleChange).toHaveBeenCalled();
});

// Test that the component calls the handleSubmit function when the user submits the form
test("calls the handleSubmit function when the user submits the form", () => {
  const wrapper = shallow(<LoginForm />);
  const handleSubmit = jest.spyOn(wrapper.instance(), "handleSubmit");

  // Simulate submitting the form
  wrapper.find("form").simulate("submit", { preventDefault: () => {} });

  // Expect the handleSubmit function to be called
  expect(handleSubmit).toHaveBeenCalled();
});

// Test that the component sends the formData to the server using axios and handles the response or the error
test("sends the formData to the server using axios and handles the response or the error", async () => {
  renderWithRouter(<LoginForm />);
  const emailInput = screen.getByLabelText("Email");
  const passwordInput = screen.getByLabelText("Password");
  const submitButton = screen.getByRole("button", { name: "Sign In" });

  // Enter a valid email and password
  fireEvent.change(emailInput, { target: { value: mockFormData.email } });
  fireEvent.change(passwordInput, { target: { value: mockFormData.password } });

  // Mock the axios post method to return a resolved promise with the mock token
  axios.post.mockResolvedValue({ data: mockToken });

  // Click the submit button
  fireEvent.click(submitButton);

  // Expect the axios post method to be called with the formData and the url
  await waitFor(() => {
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/auth/login",
      mockFormData
    );
  });

  // Mock the axios post method to return a rejected promise with the mock error
  axios.post.mockRejectedValue({ response: { data: mockError } });

  // Click the submit button
  fireEvent.click(submitButton);

  // Expect the axios post method to be called with the formData and the url
  await waitFor(() => {
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/auth/login",
      mockFormData
    );
  });

  // Expect an error message to be displayed
  await waitFor(() => {
    expect(screen.getByText(mockError)).toBeInTheDocument();
  });
});

// Test that the component navigates to the products page if the login is successful
test("navigates to the products page if the login is successful", async () => {
  const { history } = renderWithRouter(<LoginForm />);
  const emailInput = screen.getByLabelText("Email");
  const passwordInput = screen.getByLabelText("Password");
  const submitButton = screen.getByRole("button", { name: "Sign In" });

  // Enter a valid email and password
  fireEvent.change(emailInput, { target: { value: mockFormData.email } });
  fireEvent.change(passwordInput, { target: { value: mockFormData.password } });

  // Mock the axios post method to return a resolved promise with the mock token
  axios.post.mockResolvedValue({ data: mockToken });

  // Click the submit button
  fireEvent.click(submitButton);

  // Expect the axios post method to be called with the formData and the url
  await waitFor(() => {
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/auth/login",
      mockFormData
    );
  });

  // Expect the history to have the products page as the current location
  await waitFor(() => {
    expect(history.location.pathname).toBe("/products");
  });
});
*/
/*
// Import the necessary modules
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import axios from "axios";
import LoginForm from "./components/Login";

import { MemoryRouter } from "react-router-dom";

// Mock the axios post request
jest.mock("axios");
axios.post.mockResolvedValue({ data: "some-token" });

// Define a test suite
describe("LoginForm", () => {
  // Define a test case
  test("should submit the form with valid inputs and navigate to products page", async () => {
    // Render the component
    render(
      <MemoryRouter>
    <LoginForm />;
    </MemoryRouter>
    )
    // Get the input elements by their labels
    const emailInput = screen.getByLabelText(/email/i);
    const passwordInput = screen.getByLabelText(/password/i);

    // Type some valid values into the inputs
    userEvent.type(emailInput, "test@example.com");
    userEvent.type(passwordInput, "Test@1234");

    // Get the submit button by its text
    const submitButton = screen.getByRole("button", { name: /sign in/i });

    // Click the submit button
    userEvent.click(submitButton);

    // Expect the axios post request to be called with the correct data
    await waitFor(() => {
      expect(axios.post).toHaveBeenCalledWith(
        "http://localhost:8080/auth/login",
        {
          email: "test@example.com",
          password: "Test@1234",
        }
      );
    });

    // Expect the localStorage to store the token
    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith(
        "token",
        "some-token"
      );
    });

    // Expect the localStorage to store the notification flag
    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith(
        "notification",
        true
      );
    });
/*
    // Expect the navigation to products page to happen
    await waitFor(() => {
      expect(navigate).toHaveBeenCalledWith("/products");
    });
  });
});
*/
/*

import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import axios from "axios";
import LoginForm from "./components/Login";
// Import the MemoryRouter component
import { MemoryRouter } from "react-router-dom";

// Mock the axios post request
jest.mock("axios");
axios.post.mockResolvedValue({ data: "some-token" });

// Define a test suite
describe("LoginForm", () => {
  // Define a test case
  test("should submit the form with valid inputs and navigate to products page", async () => {
    // Render the component with MemoryRouter
    render(
      <MemoryRouter>
        <LoginForm />
      </MemoryRouter>
    );
    // Get the input elements by their labels
    const fillForm = (formData) => {
    const emailInput = screen.getByLabelText(/Email/i ,formData.email);
    const passwordInput = screen.getByLabelText(/Password/i,formData.password);
    
    // Type some valid values into the inputs
    userEvent.type(emailInput, "test@example.com");
    userEvent.type(passwordInput, "Test@1234");
    // Get the submit button by its text
    const submitButton = screen.getByRole("button", { name: /sign in/i });
    // Click the submit button
    userEvent.click(submitButton);
    };
    // Expect the axios post request to be called with the correct data
    await waitFor(() => {
      expect(axios.post).toHaveBeenCalledWith(
        "http://localhost:8080/auth/login",
        {
          email: "test@example.com",
          password: "Test@1234",
        }
      );
    });
    // Expect the localStorage to store the token
    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith(
        "token",
        "some-token"
      );
    });
    // Expect the localStorage to store the notification flag
    await waitFor(() => {
      expect(localStorage.setItem).toHaveBeenCalledWith(
        "notification",
        true
      );
    });
    /*
    // Expect the navigation to products page to happen
    await waitFor(() => {
      expe
  });
});
*/



import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Login from "./components/Login";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { MemoryRouter } from "react-router-dom";

// Define a mock function for axios.post
jest.mock("axios", () => ({
  post: jest.fn(() => Promise.resolve({ data: {} })),
}));

// Define a mock function for useNavigate
jest.mock("react-router-dom", () => ({
  useNavigate: jest.fn(() => jest.fn()),
}));

// Define some test data
const validFormData = {
  
  email: "testuser@example.com",
  password: "Test@1234",
  
};

const invalidFormData = {
  email: "testuser",
  password: "test",
  
};


// Define a helper function to fill the form inputs
const fillForm = (formData) => {
  userEvent.type(screen.getByLabelText(/email/i), formData.email);
  
  userEvent.type(screen.getByLabelText(/password/i), formData.password);
  
  
};

// Define the test cases
describe("LoginForm", () => {
  test("renders the form with the required inputs and buttons", () => {
    // Render the component
    //<MemoryRouter>
    render(<Login/>);
    //</MemoryRouter>

    // Check if the inputs and buttons are present
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
   
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    
  
    expect(screen.getByRole("button", { name: /sign in/i })).toBeInTheDocument();
   // expect(screen.getByRole("link", { name: /sign in/i })).toBeInTheDocument();
  });
});

/*
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import LoginForm from "./components/Login"; // Import your login component
import { MemoryRouter } from "react-router-dom";

// Mock the userService module that handles the authentication logic
//jest.mock("../../_services/userService");
//import { userService } from "../../_services";

// Define a test suite for the login component
describe("Login component", () => {
  // Define a test case for rendering the login form
  test("renders the login form", () => {
    // Render the login component
    <MemoryRouter>
    render(<LoginForm />);
    </MemoryRouter>
    // Expect to find the logo, email, password, and login button elements
   // expect(screen.getByAltText("There is a notification image")).toBeInTheDocument();
   // expect(screen.getByAltText("There is an image")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Email")).toBeInTheDocument();
    expect(screen.getByPlaceholderText("Password")).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /me connecter/i })).toBeInTheDocument();
  });
/*
  // Define a test case for submitting the login form with valid credentials
  test("submits the login form with valid credentials", async () => {
    // Render the login component
    render(<Login />);

    // Mock the userService.login function to return a fake user object
    //userService.login.mockResolvedValue({
      //idSession: "123456",
      // Add other user properties as needed
    });

    // Get the email, password, and login button elements
    const emailInput = screen.getByPlaceholderText("Email");
    const passwordInput = screen.getByPlaceholderText("Password");
    const loginButton = screen.getByRole("button", { name: /me connecter/i });

    // Type the valid email and password values
    userEvent.type(emailInput, "root@email.com");
    userEvent.type(passwordInput, "root");

    // Click the login button
    userEvent.click(loginButton);

    // Expect the userService.login function to be called with the correct arguments
    await waitFor(() => {
      expect(userService.login).toHaveBeenCalledWith({
        login: "root@email.com",
        motdepasse: "root",
      });
    });

    // Expect the login component to navigate to the dashboard screen
    // You can use a mock function or a spy to check the navigation logic
    // For example, if you use react-router-dom, you can do something like this:
    // const history = createMemoryHistory();
    // const pushSpy = jest.spyOn(history, "push");
    // render(<Login history={history} />);
    // expect(pushSpy).toHaveBeenCalledWith("/dashboard");
  });
  

  // Define a test case for submitting the login form with invalid credentials
  test("submits the login form with invalid credentials", async () => {
    // Render the login component
    <MemoryRouter>
    render(<LoginForm />);
    </MemoryRouter>

    // Mock the userService.login function to throw an error
    //userService.login.mockRejectedValue(new Error("Invalid credentials"));

    // Get the email, password, and login button elements
    //const emailInput = screen.getByPlaceholderText("Email");
    const passwordInput = screen.getByPlaceholderText("Password");
    const loginButton = screen.getByRole("button", { name: /me connecter/i });

    // Type the invalid email and password values
    userEvent.type(emailInput, "wrong@email.com");
    userEvent.type(passwordInput, "wrong");

    // Click the login button
    userEvent.click(loginButton);
 /*
    // Expect the userService.login function to be called with the incorrect arguments
    await waitFor(() => {
      expect(userService.login).toHaveBeenCalledWith({
        login: "wrong@email.com",
        motdepasse: "wrong",
      });
    });

    // Expect the login component to show a toast message with the error
    expect(screen.getByText(/votre login information pas correct/i)).toBeInTheDocument();
  });
*/