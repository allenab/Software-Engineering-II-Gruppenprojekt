package at.uni.net.packages.response;

public class MessageResponse {
    private boolean success;

    public MessageResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccessful() {
        return success;
    }
}
