IF NOT EXISTS(SELECT * FROM sys.all_objects WHERE name ='MSGs')
BEGIN
CREATE TABLE MSGs(
	[message_id] INT IDENTITY(1,1) PRIMARY KEY,
	[message_type] VARCHAR(32),
	[message] VARCHAR(MAX),
	[sender_user_id] INT NOT NULL,
	[receiver_user_id] INT NOT NULL,
	[sending_date] VARCHAR(40),
	[receiving_date] VARCHAR(40),
	[is_send] VARCHAR(40),
	[is_received] VARCHAR(40),
	[is_seen] VARCHAR(40)
)
END
GO
