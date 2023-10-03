import { render, screen, fireEvent } from "@testing-library/react";
import ProductGrid from "./components/ProductGrid";

// Define a mock product and a mock order function
const mockProduct = {
  name: "Test Product",
  image: "test.jpg",
  description: "This is a test product",
  price: 10,
};

const mockOrder = jest.fn();

// Write a test case to check if the component renders correctly
test("renders product card with image, name, description, price, quantity selector and order button", () => {
  // Render the component with the mock product and order function as props
  render(<ProductGrid product={mockProduct} order={mockOrder} />);

  // Expect to find the image element with the correct src attribute
  const image = screen.getByRole("img");
  expect(image).toHaveAttribute("src", mockProduct.image);

  // Expect to find the name element with the correct text content
  const name = screen.getByRole("heading", { level: 4 });
  expect(name).toHaveTextContent(mockProduct.name);

  // Expect to find the description element with the correct text content
  const description = screen.getByText(mockProduct.description);
  expect(description).toBeInTheDocument();

  // Expect to find the price element with the correct text content
  const price = screen.getByText(`$${mockProduct.price}`);
  expect(price).toBeInTheDocument();

  // Expect to find the quantity selector element with the correct value and options
  const quantity = screen.getByRole("combobox");
  expect(quantity).toHaveValue("1");
  expect(quantity).toHaveDisplayValue("1");
  expect(quantity).toHaveTextContent("12345678910");

  // Expect to find the order button element with the correct text content
  const order = screen.getByRole("button");
  expect(order).toHaveTextContent("Order");
});

// Write a test case to check if the order function is called with the correct arguments when the order button is clicked
test("calls order function with product name, quantity and price when order button is clicked", () => {
  // Render the component with the mock product and order function as props
  render(<ProductGrid product={mockProduct} order={mockOrder} />);

  // Find the quantity selector and the order button elements
  const quantity = screen.getByRole("combobox");
  const order = screen.getByRole("button");

  // Change the quantity value to 3
  fireEvent.change(quantity, { target: { value: "3" } });

  // Click the order button
  fireEvent.click(order);

  // Expect the order function to be called once with the correct arguments
  expect(mockOrder).toHaveBeenCalledTimes(1);
  expect(mockOrder).toHaveBeenCalledWith({
    name: mockProduct.name,
    qty: 3,
    price: mockProduct.price * 3,
  });
});