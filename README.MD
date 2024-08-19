# Coroutine-Driven Chat Server

## Overview

This project aims to explore Kotlin coroutines by building a simple, non-blocking chat server as a CLI application. The goal is to understand coroutine-based concurrency by implementing a basic chat system that can handle multiple users simultaneously without blocking the main thread.

## Features

- **Non-Blocking I/O**: Utilize coroutines to manage multiple client connections efficiently.
- **Basic Messaging**: Users can send and receive messages in real-time.
- **User Management**: Handles multiple users with unique usernames.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/alexn256/coroutine-driven-chat

2. **Build the Project**:

   Use Gradle or Kotlin CLI to build the project.


3. **Run the Server**:
   ```bash
   kotlin -classpath ServerApp.kt