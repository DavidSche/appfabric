package com.davidche.workflow.common.constant;

/**
 * Defining response statuses
 * 
 * @Author:David.che
 * 
 * @version: 0.1-SNAPSHOT
 * 
 * @category com.david:david-api-demo-common
 *
 */

public enum ResponseStatus {

	SUCCESSFUL(0), FAIL(1);

	private int status;

	private ResponseStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
