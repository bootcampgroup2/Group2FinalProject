import { render, screen } from '@testing-library/react';
import Inbox from './components/Inbox';
describe("Inbox", () => {
test('renders login prompt when not logged in', () => {
  render(<Inbox />);
  const linkElement = screen.getByText(/You need to login first/i);
  expect(linkElement).toBeInTheDocument();
});

});