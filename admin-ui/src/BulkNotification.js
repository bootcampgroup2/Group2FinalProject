import axios from "axios";
import React, { useState } from "react";

const BulkNotificationSender = () => {
  const [message, setMessage] = useState("");

  const handleInputChange = (event) => {
    setMessage(event.target.value);
  };

  const handleSendNotifications = () => {
    axios
      .get("http://localhost:8085/bulknotification/sendbulk")
      .then((result) => {
        setMessage(result.data);
      })
      .catch((err) => {
        setMessage(err.response.data);
      });
  };

  return (
    <div>
      <h1>Bulk Notification Sender</h1>
      <button onClick={handleSendNotifications}>Send Bulk Notifications</button>
      <h1>{message}</h1>
    </div>
  );
};

export default BulkNotificationSender;
