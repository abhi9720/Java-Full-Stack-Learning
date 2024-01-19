import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import { Container, Button, Badge } from 'react-bootstrap';
import './blogDetails.css';
import { deletePost, dislikePost, likePost } from '../../actions/blogActions';

const BlogDetail = () => {
  const dispatch = useDispatch();
  const { id } = useParams();
  const navigate = useNavigate();

  const handleDelete = (postId) => {
    dispatch(deletePost(postId));
    navigate('/');
  };

  const handleLike = (postId) => {
    dispatch(likePost(postId));  
  };

  const handleDislike = (postId) => {
    dispatch(dislikePost(postId));  
  };

  const post = useSelector((state) =>
    state.blog.posts.find((p) => p.id === parseInt(id, 10))
  );

  const [likes, setLikes] = useState(post.likes || 0);
  const [dislikes, setDislikes] = useState(post.dislikes || 0);
  

  if (!post) {
    return <div>Post not found</div>;
  }

  return (
    <Container className="position-relative mt-3">
      <div className="d-flex justify-content-between gap-1">
        <div>
          <Button
            variant="outline-dark"
            onClick={() => {
              navigate(-1);
            }}
          >
            Go Back
          </Button>
        </div>

        <div className="d-flex gap-1">
          <Button
            variant="primary"
            onClick={() => {
              navigate(`/edit/${post.id}`);
            }}
          >
            Edit
          </Button>
          <Button variant="danger" onClick={() => handleDelete(post.id)}>
            Delete
          </Button>
        </div>
      </div>

      <div className="mt-4 blogdetail-theme-card shadow-lg">
        <h2 className="fs-1 mb-2">{post.title}</h2>
        <hr />
        <p className="custom-card-text">{post.content}</p>
        <div className="btn-group" role="group" aria-label="Like and Dislike Buttons">
          <Button
            variant="success"
            onClick={() => {
              handleLike(post.id);
              setLikes(likes + 1);
            }}
          >
            Like <Badge bg="success">{likes}</Badge>
          </Button>
          <Button
            variant="danger"
            onClick={() => {
              handleDislike(post.id);
              setDislikes(dislikes + 1);
            }}
          >
            Dislike <Badge bg="danger">{dislikes}</Badge>
          </Button>
        </div>
      </div>
    </Container>
  );
};

export default BlogDetail;
