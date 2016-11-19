package com.lenovo.lps.push.marketing.common.compatibility.adapters;

import java.util.List;

import com.lenovo.lps.push.marketing.common.vo.Feedback;
import com.lenovo.lps.push.marketing.common.vo.FeedbackError;

public class AdaptedResult {

	private List<Feedback> feedbackList;
	private List<FeedbackError> errorList;
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}
	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}
	public List<FeedbackError> getErrorList() {
		return errorList;
	}
	public void setErrorList(List<FeedbackError> errorList) {
		this.errorList = errorList;
	}
	
	
}
