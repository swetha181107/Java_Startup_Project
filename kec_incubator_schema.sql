CREATE DATABASE IF NOT EXISTS kec_incubator CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE kec_incubator;
CREATE TABLE IF NOT EXISTS startups (
 startup_id INT AUTO_INCREMENT PRIMARY KEY,
 proposal_code VARCHAR(20) NOT NULL UNIQUE,
 startup_name VARCHAR(100) NOT NULL,
 category VARCHAR(30) NOT NULL,
 business_score DECIMAL(5,2) NOT NULL,
 prototype_score DECIMAL(5,2) NOT NULL,
 pitch_score DECIMAL(5,2) NOT NULL,
 impact_score DECIMAL(5,2) NOT NULL,
 feasibility_score DECIMAL(5,2) NOT NULL,
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS founders (
 founder_id INT AUTO_INCREMENT PRIMARY KEY,
 startup_id INT NOT NULL,
 student_name VARCHAR(100) NOT NULL,
 sgpa DECIMAL(4,2) NOT NULL,
 backlog_cleared BOOLEAN NOT NULL DEFAULT TRUE,
 UNIQUE KEY uq_startup_founder(startup_id, student_name),
 FOREIGN KEY (startup_id) REFERENCES startups(startup_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS pitch_scores (
 score_id INT AUTO_INCREMENT PRIMARY KEY,
 startup_id INT NOT NULL UNIQUE,
 final_score DECIMAL(6,2) NOT NULL,
 category_threshold_met BOOLEAN NOT NULL,
 eligibility_status VARCHAR(30) NOT NULL,
 evaluated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 FOREIGN KEY (startup_id) REFERENCES startups(startup_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS grant_allocations (
 allocation_id INT AUTO_INCREMENT PRIMARY KEY,
 startup_id INT NOT NULL UNIQUE,
 grant_amount DECIMAL(12,2) NOT NULL DEFAULT 0,
 grant_tier VARCHAR(20) NOT NULL,
 approval_status VARCHAR(30) NOT NULL,
 disbursement_status VARCHAR(30) NOT NULL,
 FOREIGN KEY (startup_id) REFERENCES startups(startup_id) ON DELETE CASCADE
);
