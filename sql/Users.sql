IF NOT EXISTS(SELECT * FROM sys.all_objects WHERE name ='Users')
BEGIN
CREATE TABLE Users(
	[user_id] INT IDENTITY(1,1) PRIMARY KEY,
	[user_name] VARCHAR(50),
	[password] VARCHAR(20),
	[name] VARCHAR(50),
	[email] VARCHAR(50),
	[mobile_no] VARCHAR(50),
	is_logged_in INT NOT NULL
)
END
GO

