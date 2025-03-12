# Dailymotion Video Browser

This is a technical test project for Dailymotion's Android/KMP developer position. The application allows users to browse videos from the Dailymotion API, load additional content dynamically, and play videos in a full-screen player.

## Features

- Fetch and display a list of videos from the Dailymotion API (`/video` endpoint)
- Infinite scrolling to load additional videos dynamically
- Full-screen video playback using Media3 with ExoPlayer
- Modern Android architecture with MVVM and Clean Architecture
- Fully built with Jetpack Compose, including navigation
- Cross-platform support with Kotlin Multiplatform (KMP) only Android

## Architecture

The project follows **Clean Architecture** principles and is structured into multiple modules to separate concerns and improve maintainability.

### Modules Overview:

- **app**: Android entry point (Android-specific code)
- **common**: Contains shared logic and navigation accessible across all platforms
- **present**: Responsible for transforming domain models into UI models (mapping to `Property` objects)
- **design-system-property**: Defines UI properties in a simple, platform-agnostic manner
- **design-system**: Jetpack Compose components, each corresponding to a `Property` from `design-system-property`
- **domain**: Business logic layer, defining use cases and repository interfaces
- **data**: Repository implementations, fetching data from remote sources (API) and mapping them to domain models

This modular architecture, combined with Kotlin Multiplatform, allows code sharing across different platforms while maintaining flexibility for platform-specific implementations.

## Tech Stack

- **Kotlin Multiplatform (KMP)**
- **Jetpack Compose** (UI & Navigation)
- **MVVM Architecture**
- **Clean Architecture**
- **Ktor** (for API calls)
- **Media3 with ExoPlayer** (for video playback)
- **Coil** (for image loading)

## API Usage

The app fetches videos from Dailymotion’s public API:

Endpoint: https://api.dailymotion.com/videos

Required fields: id, title, description, thumbnail_url, created_time

Pagination: Implemented using a next cursor for infinite scrolling.

## Video Playback

When a video is selected, it is played in a full-screen player using Media3 with ExoPlayer.

For demonstration purposes, all videos use this stream:

https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8.

## License

This project is for evaluation purposes only and is not intended for commercial use.
