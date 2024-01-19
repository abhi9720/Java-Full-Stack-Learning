const initialState = {
    posts: [],
    currentPost: null,
  };
  
  const blogReducer = (state = initialState, action) => {
    switch (action.type) {
      case 'ADD_POST':
        const newPosts = [...state.posts, action.payload];
        localStorage.setItem('blogPosts', JSON.stringify(newPosts));
        return {
          ...state,
          posts: newPosts,
        };
  
      case 'EDIT_POST':
        const editedPost = action.payload;
        const updatedPosts = state.posts.map((post) =>
          post.id === editedPost.id ? editedPost : post
        );
        localStorage.setItem('blogPosts', JSON.stringify(updatedPosts));
        return {
          ...state,
          posts: updatedPosts,
        };
  
      case 'DELETE_POST':
        const postIdToDelete = action.payload;
        const filteredPosts = state.posts.filter((post) => post.id !== postIdToDelete);
        localStorage.setItem('blogPosts', JSON.stringify(filteredPosts));
        return {
          ...state,
          posts: filteredPosts,
        };
  
      case 'LIKE_POST':
        const likedPostId = action.payload;
        const updatedLikedPosts = state.posts.map((post) =>
          post.id === likedPostId ? { ...post, likes: (post.likes || 0) + 1 } : post
        );
        localStorage.setItem('blogPosts', JSON.stringify(updatedLikedPosts));
        return {
          ...state,
          posts: updatedLikedPosts,
        };
  
      case 'DISLIKE_POST':
         const dislikedPostId = action.payload;
        const updatedDislikedPosts = state.posts.map((post) =>
          post.id === dislikedPostId ? { ...post, dislikes: (post.dislikes || 0) + 1 } : post
        );
        console.log(updatedDislikedPosts);
        localStorage.setItem('blogPosts', JSON.stringify(updatedDislikedPosts));
        return {
          ...state,
          posts: updatedDislikedPosts,
        };
  
      case 'GET_POST':
        const postIdToGet = action.payload;
        const postToGet = state.posts.find((post) => post.id === postIdToGet);
        return {
          ...state,
          currentPost: postToGet,
        };
  
      case 'GET_ALL_POSTS':
        const storedPosts = JSON.parse(localStorage.getItem('blogPosts')) || [];
        console.log(storedPosts);
        return {
          ...state,
          posts: storedPosts,
        };

        case 'CLEAR_CURRENT_POST':
          return {
            ...state,
            currentPost : null
          }
  
      default:
        return state;
    }
  };
  
  export default blogReducer;
  