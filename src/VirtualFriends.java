import java.util.*;

public class VirtualFriends {
    static Scanner scn;
    HashSet<Friend> network = new HashSet<>();
    int friendsSize;

    private void solve() {
        friendsSize = scn.nextInt();
        for (int i = 0; i < friendsSize; i++) {
            Friend lastFriend = createFriendsAndAddThemToCollection();
            bfs(lastFriend);
        }

    }


    public void bfs(Friend friend) {
        LinkedList<String> kolekcja = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        kolekcja.add(friend.name);

        int sizeOfNetwork = 0;
        while (!kolekcja.isEmpty()) {
            Friend node = getFriendByName(kolekcja.pop(), network);
            visited.add(node.name);
            for (Friend fr : node.friends) {
                if (!visited.contains(fr.name) && !kolekcja.contains(fr.name)) {
                    kolekcja.add(fr.name);
                }
            }
            sizeOfNetwork++;
        }

        System.out.println(sizeOfNetwork);

    }

    private Friend createFriendsAndAddThemToCollection() {
        scn = new Scanner(System.in);
        String input = scn.nextLine();
        StringTokenizer line = new StringTokenizer(input);
        Friend friend1 = new Friend(line.nextToken());
        Friend friend2 = new Friend(line.nextToken());


        if (!networkContains(friend1.name)) {
            network.add(friend1);
            friend1.addFriend(friend2);
        } else {
            friend1 = getFriendByName(friend1.name, network);
            friend1.addFriend(friend2);
        }
        if (!networkContains(friend2.name)) {
            network.add(friend2);
            friend2.addFriend(friend1);
        } else {
            friend2 = getFriendByName(friend2.name, network);
            friend2.addFriend(friend1);
        }

        return friend2;


    }

    private Friend getFriendByName(String name, Collection<Friend> collection) {
        for (Friend friend : collection) {
            if (friend.name.equals(name)) return friend;
        }
        return null;
    }

    private boolean networkContains(String name) {
        for (Friend friend : network) {
            if (friend.name.equals(name)) return true;
        }
        return false;
    }

    private class Friend {

        HashSet<Friend> friends = new HashSet<>();

        String name;

        public Friend(String name) {
            this.name = name;
        }

        void addFriend(Friend friend) {
            if (!hasFriend(friend)) {
                friends.add(friend);
            }
        }

        private Friend getFriendByName(String name) {
            for (Friend friend : friends) {
                if (friend.name.equals(name)) return friend;
            }
            return null;
        }

        private boolean hasFriend(Friend friend) {
            for (Friend friend1 : friends) {
                if (friend1.name.equals(friend.name)) return true;
            }
            return false;
        }

    }


    public static void main(String[] args) {


        scn = new Scanner(System.in);
        int n = scn.nextInt();
        for (int i = 0; i < n; i++) {
            VirtualFriends problem = new VirtualFriends();
            problem.solve();
        }

    }
}
