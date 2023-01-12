package com.profilemanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.profilemanagement.model.BlockRegistry;
import com.profilemanagement.model.Followship;
import com.profilemanagement.model.UserProfile;
import com.profilemanagement.repositories.BlockRegistryRepository;
import com.profilemanagement.repositories.FollowshipRepository;
import com.profilemanagement.repositories.UserProfileRepository;

@Service
public class FollowshipService {
    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    BlockRegistryRepository blockRegistryRepository;

    @Autowired
    FollowshipRepository followshipRepository;

    public String blockUser(String username_initiator, String username_target) {
        BlockRegistry block = new BlockRegistry();
        block.setInitiator(username_initiator);
        block.setTarget(username_target);
        //0 je public visibility

        Followship followship = followshipRepository.findByInitiatorAndTarget(username_initiator, username_target);

        if(followship != null) {
            followshipRepository.delete(followship);
        }
        Followship follower = followshipRepository.findByInitiatorAndTarget(username_target, username_initiator);
        if(follower != null) {
            followshipRepository.delete(follower);
        }
        blockRegistryRepository.save(block);

        return "User " + username_target + " successfully blocked.";
    }

    public String unblockUser(String initiator, String target) {
        BlockRegistry block = blockRegistryRepository.findByInitiatorAndTarget(initiator, target);

        blockRegistryRepository.delete(block);

        return "User " + target + " successfully unblocked";
    }

    public Boolean isUserBlocked(String initiator, String target) {
        BlockRegistry block = blockRegistryRepository.findByInitiatorAndTarget(initiator, target);

        if(block != null)
            return true;

        return false;
    }

    public Boolean isUserFollowed(String initiator, String target) {
        Followship followship = followshipRepository.findByInitiatorAndTarget(initiator, target);

        if(followship != null)
            return true;

        return false;
    }

    public String followUser(String username_initiator, String username_target) {
        Followship followship = new Followship();
        followship.setInitiator(username_initiator);
        followship.setTarget(username_target);
        //0 je public visibility
        followship.setApproved(userProfileRepository.findByUsername(username_target).getVisibility() == 0 ? 1 : 0);

        followshipRepository.save(followship);

        return "Follow " + (userProfileRepository.findByUsername(username_target).getVisibility() == 0 ? "Successful" : "Pending");
    }

    public String AnswerFollowRequest(String initiator, String target, int response) {
        Followship followship = followshipRepository.findByInitiatorAndTarget(initiator, target);

        //accepted if 1
        if(response == 1) {
            followship.setApproved(1);
            followshipRepository.save(followship);
            return "Follow request from " + initiator + " accepted";
        }

        //delete if 0
        followshipRepository.delete(followship);
        return "Follow request from " + initiator + " rejected";
    }

    public String UnfollowUser(String initiator, String target) {
        Followship followship = followshipRepository.findByInitiatorAndTarget(initiator, target);

        followshipRepository.delete(followship);

        return "User " + target + " unfollowed";
    }

    public List<UserProfile> findAllFollowedUsers(String initiator) {
        List<Followship> followships = followshipRepository.findByInitiatorAndApproved(initiator, 1);
        List<UserProfile> users = new ArrayList<UserProfile>();

        for(Followship followship: followships) {
            users.add(userProfileRepository.findByUsername(followship.getTarget()));
        }

        return users;
    }

    public List<UserProfile> findAllConnectedUsers(String initiator) {
        List<Followship> followships = followshipRepository.findByInitiatorAndApproved(initiator, 1);
        List<Followship> followedBy = followshipRepository.findByTargetAndApproved(initiator, 1);
        List<UserProfile> users = new ArrayList<UserProfile>();

        for(Followship followship: followships) {
            users.add(userProfileRepository.findByUsername(followship.getTarget()));
        }

        for(Followship followship: followedBy) {
            boolean isFollower = false;
            for(UserProfile profile : users) {
                if (followship.getInitiator().equals(profile.getUsername())) {
                    isFollower = true;
                    break;
                }
            }
            if (!isFollower) {
                users.add(userProfileRepository.findByUsername(followship.getInitiator()));
            }
        }
        return users;
    }
    public List<UserProfile> findAllPendingFollows(String target) {
        List<Followship> followships = followshipRepository.findByTargetAndApproved(target, 0);
        List<UserProfile> users = new ArrayList<UserProfile>();

        for(Followship followship: followships) {
            users.add(userProfileRepository.findByUsername(followship.getInitiator()));
        }

        return users;
    }

}
