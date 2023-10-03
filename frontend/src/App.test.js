import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import RegistrationForm from "./components/Register";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
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
  userName: "testuser",
  password: "Test@1234",
  email: "testuser@example.com",
  dOB: "2000-01-01",
};

const invalidFormData = {
  userName: "test",
  password: "test",
  email: "testuser",
  dOB: "2025-01-01",
};

// Define a helper function to fill the form inputs
const fillForm = (formData) => {
  userEvent.type(screen.getByLabelText(/userName/i), formData.userName);
  userEvent.type(screen.getByLabelText(/password/i), formData.password);
  userEvent.type(screen.getByLabelText(/email/i), formData.email);
  userEvent.type(screen.getByLabelText(/dOB/i), formData.dOB);
};

// Define the test cases
describe("RegistrationForm", () => {
  test("renders the form with the required inputs and buttons", () => {
    // Render the component
    render(<RegistrationForm/>);

    // Check if the inputs and buttons are present
    expect(screen.getByLabelText(/userName/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/dOB/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /sign up/i })).toBeInTheDocument();
    expect(screen.getByRole("link", { name: /sign in/i })).toBeInTheDocument();

/*
  test("submits the form data and navigates to the home page when the inputs are valid", async () => {
    // Render the component
    render(<RegistrationForm />);

    // Fill the form with valid data
    fillForm(validFormData);

    // Click the sign up button
    fireEvent.click(screen.getByRole("button", { name: /sign up/i }));

    // Check if the axios.post function is called with the correct data and url
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/user/adduser",
      validFormData
    );

    // Check if the useNavigate function is called to redirect to the home page
    //expect(useNavigate()).toHaveBeenCalledWith("/");
  });*/
});
});




/*//Import React Testing Library and Jest
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import RegistrationForm from "./components/Register"
import axios from "axios";
// Define a mock function for axios.post
jest.mock("axios");


// Define a test suite for the registration form
describe("RegistrationForm", () => {
  // Define a test case for rendering the form
  test("renders the form", () => {
    // Render the form component
    render(<RegistrationForm />);

    // Check if the form elements are present
    expect(screen.getByLabelText(/userName/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Password/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/Email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/dOB/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /Sign Up/i })).toBeInTheDocument();
    expect(screen.getByText(/Already have an account/i)).toBeInTheDocument();
  });

  // Define a test case for submitting the form with valid data
  test("submits the form with valid data", async () => {
    // Render the form component
    render(<RegistrationForm />);

    // Fill in the form fields with valid data
    fireEvent.change(screen.getByLabelText(/userName/i), {
      target: { value: "testuser" },
    });
    fireEvent.change(screen.getByLabelText(/Password/i), {
      target: { value: "Test@1234" },
    });
    fireEvent.change(screen.getByLabelText(/Email/i), {
      target: { value: "testuser@test.com" },
    });
    fireEvent.change(screen.getByLabelText(/dOB/i), {
      target: { value: "2000-01-01" },
    });

    // Mock the axios.post response with a success status
    axios.post.mockResolvedValue({ status: 200 });

    // Click the submit button
    fireEvent.click(screen.getByRole("button", { name: /Sign Up/i }));

    // Wait for the axios.post to resolve
    await waitFor(() => expect(axios.post).toHaveBeenCalled());

    // Check if the axios.post was called with the correct data
    expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/user/adduser", {
      userName: "testuser",
      password: "Test@1234",
      email: "testuser@test.com",
      dOB: "2000-01-01",
    });
  });

  // Define a test case for submitting the form with invalid data
  test("submits the form with invalid data", async () => {
    // Render the form component
    render(<RegistrationForm />);

    // Fill in the form fields with invalid data
    fireEvent.change(screen.getByLabelText(/userName/i), {
      target: { value: "test" },
    });
    fireEvent.change(screen.getByLabelText(/Password/i), {
      target: { value: "test" },
    });
    fireEvent.change(screen.getByLabelText(/Email/i), {
      target: { value: "test" },
    });
    fireEvent.change(screen.getByLabelText(/dOB/i), {
      target: { value: "2023-01-01" },
    });

    // Mock the axios.post response with an error status and message
    axios.post.mockRejectedValue({
      response: { data: "Email already exists" },
    });

    // Click the submit button
    fireEvent.click(screen.getByRole("button", { name: /Sign Up/i }));

    // Wait for the axios.post to reject
    await waitFor(() => expect(axios.post).toHaveBeenCalled());

    // Check if the axios.post was called with the invalid data
    expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/user/adduser", {
      userName: "test",
      password: "test",
      email: "test",
      dOB: "2023-01-01",
    });

    // Check if the error messages are displayed
    expect(screen.getByText(/userName must be at least 5 characters long/i)).toBeInTheDocument();
    expect(screen.getByText(/Password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character/i)).toBeInTheDocument();
    expect(screen.getByText(/Email is not valid/i)).toBeInTheDocument();
    expect(screen.getByText(/please select a date in past/i)).toBeInTheDocument();
    expect(screen.getByText(/Email already exists/i)).toBeInTheDocument();
  });
});
*/
/*

// Import the required modules
import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom/extend-expect";
import RegistrationForm from "./components/Register"
import axios from "axios";

// Mock the axios module to simulate the API calls
jest.mock("axios");

// Define a helper function to fill the form fields with valid data
const fillForm = () => {
  fireEvent.change(screen.getByLabelText(/username/i), {
    target: { value: "testuser" },
  });
  fireEvent.change(screen.getByLabelText(/password/i), {
    target: { value: "Test@1234" },
  });
  fireEvent.change(screen.getByLabelText(/email/i), {
    target: { value: "testuser@example.com" },
  });
  fireEvent.change(screen.getByLabelText(/dob/i), {
    target: { value: "2000-01-01" },
  });
};

// Test cases for the registration form
describe("RegistrationForm", () => {
  // Test case for rendering the form
  test("renders the form with the required fields and labels", () => {
    render(<RegistrationForm />);
    expect(screen.getByText(/sign up/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/username/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/dob/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /sign up/i })).toBeInTheDocument();
    expect(screen.getByRole("link", { name: /sign in/i })).toBeInTheDocument();
  });

  // Test case for validating the username field
  test("validates the username field and shows an error message if it is less than 5 characters", async () => {
    render(<RegistrationForm />);
    fireEvent.change(screen.getByLabelText(/username/i), {
      target: { value: "test" },
    });
    fireEvent.blur(screen.getByLabelText(/username/i));
    await waitFor(() =>{
      expect(screen.getByText(/username must be at least 5 characters long/i))
        .toBeInTheDocument()
    }
    );
  });

  // Test case for validating the password field
  test("validates the password field and shows an error message if it does not meet the criteria", async () => {
    render(<RegistrationForm />);
    fireEvent.change(screen.getByLabelText(/password/i), {
      target: { value: "test" },
    });
    fireEvent.blur(screen.getByLabelText(/password/i));
    await waitFor(() =>{
      expect(
        screen.getByText(
          /password should be minimum 8 character and contain atleast 1 uppercase,1 lowercase,1 digit, 1 special character/i
        )
      ).toBeInTheDocument()
        }
    );
  });

  // Test case for validating the email field
  test("validates the email field and shows an error message if it is not valid", async () => {
    render(<RegistrationForm />);
    fireEvent.change(screen.getByLabelText(/email/i), {
      target: { value: "testuser" },
    });
    fireEvent.blur(screen.getByLabelText(/email/i));
    await waitFor(() =>{
      expect(screen.getByText(/email is not valid/i)).toBeInTheDocument()
    }
    );
  });

  // Test case for validating the dob field
  test("validates the dob field and shows an error message if it is not in the past", async () => {
    render(<RegistrationForm />);
    fireEvent.change(screen.getByLabelText(/dob/i), {
      target: { value: "2100-01-01" },
    });
    fireEvent.blur(screen.getByLabelText(/dob/i));
    await waitFor(() =>{
      expect(screen.getByText(/please select a date in past/i)).toBeInTheDocument()
    }
    );
  });

  // Test case for submitting the form with valid data
  test("submits the form with valid data and redirects to the login page", async () => {
    render(<RegistrationForm />);
    fillForm();
    axios.post.mockResolvedValue({ data: "success" });
    fireEvent.click(screen.getByRole("button", { name: /sign up/i }));
    await waitFor(() => expect(axios.post).toHaveBeenCalledTimes(1));
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/user/adduser",
      {
        userName: "testuser",
        password: "Test@1234",
        email: "testuser@example.com",
        dOB: "2000-01-01",
      }
    );
    // Add your logic to check if the user is redirected to the login page
  });

  // Test case for submitting the form with an existing email
  test("submits the form with an existing email and shows an error message", async () => {
    render(<RegistrationForm />);
    fillForm();
    axios.post.mockRejectedValue({ response: { data: "email already exists" } });
    fireEvent.click(screen.getByRole("button", { name: /sign up/i }));
    await waitFor(() => expect(axios.post).toHaveBeenCalledTimes(1));
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/user/adduser",
      {
        userName: "testuser",
        password: "Test@1234",
        email: "testuser@example.com",
        dOB: "2000-01-01",
      }
    );
    await waitFor(() =>{
      expect(screen.getByText(/email already exists/i)).toBeInTheDocument()
    }
    );
  });
});
*/
/*

import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import Register from "./components/Register"; // The component to be tested
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
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
  userName: "testuser",
  password: "Test@1234",
  email: "testuser@example.com",
  dOB: "2000-01-01",
};

const invalidFormData = {
  userName: "test",
  password: "test",
  email: "testuser",
  dOB: "2025-01-01",
};

// Define a helper function to fill the form inputs
const fillForm = (formData) => {
  userEvent.type(screen.getByLabelText(/userName/i), formData.userName);
  userEvent.type(screen.getByLabelText(/password/i), formData.password);
  userEvent.type(screen.getByLabelText(/email/i), formData.email);
  userEvent.type(screen.getByLabelText(/dOB/i), formData.dOB);
};

// Define the test cases
describe("RegistrationForm", () => {
  test("renders the form with the required inputs and buttons", () => {
    // Render the component
    render(<Register />);

    // Check if the inputs and buttons are present
    expect(screen.getByLabelText(/userName/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/password/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/email/i)).toBeInTheDocument();
    expect(screen.getByLabelText(/dOB/i)).toBeInTheDocument();
    expect(screen.getByRole("button", { name: /sign up/i })).toBeInTheDocument();
    expect(screen.getByRole("link", { name: /sign in/i })).toBeInTheDocument();

/*
  test("submits the form data and navigates to the home page when the inputs are valid", async () => {
    // Render the component
    render(<RegistrationForm />);

    // Fill the form with valid data
    fillForm(validFormData);

    // Click the sign up button
    fireEvent.click(screen.getByRole("button", { name: /sign up/i }));

    // Check if the axios.post function is called with the correct data and url
    expect(axios.post).toHaveBeenCalledWith(
      "http://localhost:8080/user/adduser",
      validFormData
    );

    // Check if the useNavigate function is called to redirect to the home page
    //expect(useNavigate()).toHaveBeenCalledWith("/");
  });
});
});
*/

