package com.kosmo.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kosmo.project.dao.PostDAO;
import com.kosmo.project.dto.Post;

@RestController
public class PostController {
	
	@Autowired
	private PostDAO postDao;

	//모든 게시글 조회
	@GetMapping("/post")
	public ResponseEntity<List<Post>> getAllPosts(){
		List<Post> posts = postDao.getAllPosts();
		if(posts.size() > 0 ) {
			return ResponseEntity.ok(posts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//게시글 추가
	@PostMapping("/post/add")
	public ResponseEntity<Void> addPost(@Valid @RequestBody Post post){
		boolean result = postDao.addPost(post);
		if(result) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	//게시글 조회
	@GetMapping("/post/{id}")
	public ResponseEntity<Post> getPost(@PathVariable(value="id") int id){
		Post post = postDao.getPostById(id);
		if(post == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(post);
	}
	
	//게시글 수정
	@PutMapping("/post/{id}")
	public ResponseEntity<Void> updatePost(@PathVariable(value="id") int id, @Valid @RequestBody Post post){
		 boolean result = postDao.updatePost(id, post);
		  if(result) {
			  return ResponseEntity.noContent().build();
		  }else {
		      return ResponseEntity.notFound().build();
		  }
	}
	
	//게시글 삭제
	@DeleteMapping("/post/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value="id") int id){
		boolean result = postDao.deletePost(id);
		if(result) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}