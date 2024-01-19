export const addPost = (post) => ({
    type: 'ADD_POST',
    payload: post,
  });
  
export const editPost = (post) => ({
    type: 'EDIT_POST',
    payload: post,
  });
  
  export const deletePost = (postId) => ({
    type: 'DELETE_POST',
    payload: postId,
  });
  
  export const getPost = (postId) => ({
    type: 'GET_POST',
    payload: postId,
  });
  
  export const getAllPosts = () => ({
    type: 'GET_ALL_POSTS',
  });

  export const dislikePost = (postId) => ({
    type: 'DISLIKE_POST',
    payload: postId,
  });

  export const likePost = (postId) => ({
    type: 'LIKE_POST',
    payload: postId,
  });

  export const clearCurrentPost =  (postId) =>({
    type : 'CLEAR_CURRENT_POST'
  })
  