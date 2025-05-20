package services;

import model.Feedback;
import repository.FeedbackRepository;

public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService() {
        this.feedbackRepository = new FeedbackRepository();
    }

    public boolean submitFeedback(Feedback feedback) {

        if (feedback.getRating() == null || feedback.getRating().isBlank()) {
            return false;
        }

        if (feedback.getComment() == null || feedback.getComment().isBlank()) {
            return false;
        }

        return feedbackRepository.saveFeedback(feedback);
    }
}
