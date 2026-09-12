# Science Forum Platform - API Documentation

## 📖 API Overview

This document describes the RESTful API for the Science Forum Platform. All endpoints are prefixed with `/api/v1`.

**Base URL**: `http://localhost:8081/api/v1`

## 🔑 Authentication

All protected endpoints require a valid JWT token in the Authorization header:

```http
Authorization: Bearer <JWT_TOKEN>
```

### Login Endpoint
```http
POST /auth/login
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "password123"
}
```

**Response**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": "uuid-here",
    "username": "username",
    "email": "user@example.com",
    "profile": {...}
  }
}
```

---

## 👤 User Management APIs

### 1. Register User
```http
POST /auth/register
Content-Type: application/json

{
  "username": "newuser",
  "email": "user@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe"
}
```

**Response**: `201 Created`
```json
{
  "id": "user-uuid",
  "username": "newuser",
  "email": "user@example.com",
  "createdAt": "2026-09-12T10:30:00Z"
}
```

### 2. Get User Profile
```http
GET /users/:userId
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`
```json
{
  "id": "user-uuid",
  "username": "john_doe",
  "email": "john@example.com",
  "profile": {
    "bio": "Science enthusiast",
    "avatar": "https://...",
    "expertise": ["Physics", "Philosophy"],
    "joinDate": "2026-01-01"
  },
  "stats": {
    "postsCount": 42,
    "followersCount": 100,
    "followingCount": 50
  }
}
```

### 3. Update User Profile
```http
PUT /users/:userId
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "bio": "Updated bio",
  "avatar": "https://...",
  "expertise": ["Physics", "Philosophy", "Science"],
  "language": "en"
}
```

**Response**: `200 OK`

### 4. Get User by Username
```http
GET /users/username/:username
```

### 5. Delete User Account
```http
DELETE /users/:userId
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 6. Follow User
```http
POST /users/:userId/follow
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 7. Unfollow User
```http
DELETE /users/:userId/follow
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 8. Get User's Followers
```http
GET /users/:userId/followers?page=0&size=20
```

**Response**: `200 OK`
```json
{
  "content": [...],
  "page": 0,
  "size": 20,
  "totalElements": 150
}
```

---

## 📝 Post APIs

### 1. Create Post
```http
POST /posts
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "title": "Understanding Quantum Physics",
  "content": "<p>Detailed content here...</p>",
  "hub": "SCIENCE",
  "category": "Physics",
  "tags": ["quantum", "physics", "science"],
  "attachments": []
}
```

**Response**: `201 Created`
```json
{
  "id": "post-uuid",
  "title": "Understanding Quantum Physics",
  "content": "<p>Detailed content here...</p>",
  "author": {...},
  "hub": "SCIENCE",
  "category": "Physics",
  "tags": ["quantum", "physics", "science"],
  "stats": {
    "likes": 0,
    "comments": 0,
    "views": 0
  },
  "createdAt": "2026-09-12T10:30:00Z"
}
```

### 2. Get Posts (with filtering)
```http
GET /posts?hub=SCIENCE&category=Physics&page=0&size=20&sort=createdAt,desc
```

**Query Parameters**:
- `hub`: STORY | SCIENCE
- `category`: Category name
- `tags`: Comma-separated tags
- `search`: Full-text search
- `page`: Page number (0-indexed)
- `size`: Results per page
- `sort`: Sort field and direction (field,asc|desc)

**Response**: `200 OK`
```json
{
  "content": [...],
  "page": 0,
  "size": 20,
  "totalElements": 500
}
```

### 3. Get Single Post
```http
GET /posts/:postId
```

**Response**: `200 OK`

### 4. Update Post
```http
PUT /posts/:postId
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "title": "Updated title",
  "content": "Updated content...",
  "category": "Physics",
  "tags": ["quantum", "physics"]
}
```

**Response**: `200 OK`

### 5. Delete Post
```http
DELETE /posts/:postId
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 6. Like Post
```http
POST /posts/:postId/like
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 7. Unlike Post
```http
DELETE /posts/:postId/like
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 8. Save/Favorite Post
```http
POST /posts/:postId/favorite
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 9. Get User's Posts
```http
GET /users/:userId/posts?page=0&size=20
```

**Response**: `200 OK`

### 10. Search Posts
```http
GET /posts/search?q=quantum+physics&page=0&size=20
```

**Response**: `200 OK`

---

## 💬 Comment APIs

### 1. Create Comment
```http
POST /posts/:postId/comments
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "content": "Great post! Here's my perspective...",
  "attachments": []
}
```

**Response**: `201 Created`
```json
{
  "id": "comment-uuid",
  "postId": "post-uuid",
  "author": {...},
  "content": "Great post! Here's my perspective...",
  "likes": 0,
  "createdAt": "2026-09-12T10:45:00Z"
}
```

### 2. Get Post Comments
```http
GET /posts/:postId/comments?page=0&size=20&sort=createdAt,desc
```

**Response**: `200 OK`
```json
{
  "content": [...],
  "page": 0,
  "size": 20,
  "totalElements": 42
}
```

### 3. Get Single Comment
```http
GET /comments/:commentId
```

**Response**: `200 OK`

### 4. Update Comment
```http
PUT /comments/:commentId
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "content": "Updated comment content..."
}
```

**Response**: `200 OK`

### 5. Delete Comment
```http
DELETE /comments/:commentId
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 6. Like Comment
```http
POST /comments/:commentId/like
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 7. Reply to Comment
```http
POST /comments/:commentId/replies
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "content": "I agree with your point..."
}
```

**Response**: `201 Created`

---

## 🔔 Notification APIs

### 1. Get Notifications
```http
GET /notifications?page=0&size=20&unreadOnly=false
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`
```json
{
  "content": [
    {
      "id": "notification-uuid",
      "type": "COMMENT",
      "message": "John commented on your post",
      "relatedEntity": {...},
      "read": false,
      "createdAt": "2026-09-12T11:00:00Z"
    }
  ],
  "unreadCount": 5
}
```

### 2. Mark Notification as Read
```http
PUT /notifications/:notificationId/read
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 3. Mark All as Read
```http
PUT /notifications/mark-all-read
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`

### 4. Delete Notification
```http
DELETE /notifications/:notificationId
Authorization: Bearer <TOKEN>
```

**Response**: `204 No Content`

### 5. Update Notification Preferences
```http
PUT /users/:userId/notification-preferences
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "enableCommentNotifications": true,
  "enableLikeNotifications": true,
  "enableFollowNotifications": true,
  "enableEmailNotifications": false
}
```

**Response**: `200 OK`

---

## 🔍 Search APIs

### 1. Full-Text Search
```http
GET /search?q=quantum+physics&type=POSTS&page=0&size=20
```

**Query Parameters**:
- `q`: Search query
- `type`: POSTS | USERS | COMMENTS | ALL
- `hub`: STORY | SCIENCE | ALL
- `page`: Page number
- `size`: Results per page

**Response**: `200 OK`
```json
{
  "posts": [...],
  "users": [...],
  "comments": [...],
  "totalResults": 150
}
```

---

## 🤖 AI APIs

### 1. Get Recommended Posts
```http
GET /recommendations/posts?limit=10
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`
```json
{
  "posts": [...],
  "reason": "Based on your interests in Physics"
}
```

### 2. Generate Post Summary
```http
POST /ai/summarize
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "postId": "post-uuid",
  "maxLength": 200
}
```

**Response**: `200 OK`
```json
{
  "summary": "This post discusses...",
  "keywords": ["quantum", "physics"]
}
```

### 3. Moderate Content
```http
POST /ai/moderate
Authorization: Bearer <TOKEN>
Content-Type: application/json

{
  "content": "Some user-generated content",
  "type": "POST"
}
```

**Response**: `200 OK`
```json
{
  "isSafe": true,
  "score": 0.95,
  "flags": []
}
```

---

## 🌐 Category APIs

### 1. Get All Categories
```http
GET /categories
```

**Response**: `200 OK`
```json
{
  "storyCategories": ["Travel", "Personal", "Health", ...],
  "scienceCategories": ["Physics", "Biology", "Chemistry", ...]
}
```

### 2. Get Category Posts
```http
GET /categories/:categoryName/posts?page=0&size=20
```

**Response**: `200 OK`

---

## 📊 Analytics APIs

### 1. Get User Statistics
```http
GET /users/:userId/stats
```

**Response**: `200 OK`
```json
{
  "postsCount": 42,
  "commentsCount": 128,
  "followersCount": 350,
  "followingCount": 120,
  "totalLikes": 1250,
  "joinDate": "2026-01-01"
}
```

### 2. Get Post Analytics
```http
GET /posts/:postId/analytics
Authorization: Bearer <TOKEN>
```

**Response**: `200 OK`
```json
{
  "views": 1500,
  "likes": 250,
  "comments": 45,
  "favorites": 100,
  "viewsOverTime": [...]
}
```

---

## ⚙️ System APIs

### 1. Health Check
```http
GET /health
```

**Response**: `200 OK`
```json
{
  "status": "UP",
  "timestamp": "2026-09-12T12:00:00Z"
}
```

### 2. API Version
```http
GET /version
```

**Response**: `200 OK`
```json
{
  "version": "1.0.0",
  "buildTime": "2026-09-12T10:00:00Z"
}
```

---

## 🔐 Error Responses

All error responses follow this format:

```json
{
  "error": {
    "code": "ERROR_CODE",
    "message": "Human-readable error message",
    "timestamp": "2026-09-12T12:00:00Z",
    "path": "/api/v1/endpoint"
  }
}
```

### Common HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 403 | Forbidden |
| 404 | Not Found |
| 409 | Conflict |
| 422 | Unprocessable Entity |
| 500 | Internal Server Error |
| 503 | Service Unavailable |

---

## 🧪 API Testing

### Using cURL
```bash
# Get posts
curl -X GET "http://localhost:8081/api/v1/posts" \
  -H "Authorization: Bearer YOUR_TOKEN"

# Create post
curl -X POST "http://localhost:8081/api/v1/posts" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"title":"Test","content":"...","hub":"STORY"}'
```

### Using Postman
1. Import collection from repository
2. Set base URL: `http://localhost:8081/api/v1`
3. Configure authentication token
4. Test endpoints

---

## 📚 WebSocket APIs

### Real-time Notifications
```javascript
// Connect
const ws = new WebSocket('ws://localhost:8081/ws/notifications/:userId');

// Listen for notifications
ws.onmessage = (event) => {
  const notification = JSON.parse(event.data);
  console.log(notification);
};

// Disconnect
ws.close();
```

### Live Comments
```javascript
// Connect to post comments
const ws = new WebSocket('ws://localhost:8081/ws/posts/:postId/comments');

// Send new comment
ws.send(JSON.stringify({
  type: 'COMMENT',
  content: 'New comment...'
}));

// Listen for new comments
ws.onmessage = (event) => {
  const comment = JSON.parse(event.data);
  updateUI(comment);
};
```

---

**Last Updated**: 2026-09-12
**API Version**: 1.0.0
