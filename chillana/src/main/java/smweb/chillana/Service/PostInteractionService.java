package smweb.chillana.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smweb.chillana.model.CommentsModel;
import smweb.chillana.model.LikesModel;
import smweb.chillana.model.PostModel;
import smweb.chillana.model.UserModel;
import smweb.chillana.repository.CommentsRepository;
import smweb.chillana.repository.LikesRepository;
import smweb.chillana.repository.PostRepository;
import smweb.chillana.repository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PostInteractionService {
    private CommentsRepository commentsRepository;
    private LikesRepository likesRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostInteractionService(CommentsRepository commentsRepository,LikesRepository likesRepository,PostRepository postRepository,UserRepository userRepository) {
        this.commentsRepository = commentsRepository;
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    public void LikePost(int postId,String username) {
        UserModel userModel = userRepository.findByUsername(username);
        Optional<PostModel> post = postRepository.findById(postId);
        PostModel postModel = post.get();

        LikesModel like = new LikesModel();

        like.setPost(postModel);
        like.setUser(userModel);
        likesRepository.save(like);
    }

    public void CommentPost(int postId,String username,String usercomment) {
        UserModel userModel = userRepository.findByUsername(username);
        Optional<PostModel> post = postRepository.findById(postId);
        PostModel postModel = post.get();
        CommentsModel comment = new CommentsModel();
        comment.setPost(postModel);
        comment.setUser(userModel);
        comment.setComment(usercomment);
        comment.setTimestamp(LocalDateTime.now());
        commentsRepository.save(comment);
    }

}