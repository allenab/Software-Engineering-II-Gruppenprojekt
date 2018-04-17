package at.uni.net.packets.response;

public class MessageResponse {
    private boolean success;

    public MessageResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccessful() {
        return success;
    }
}
