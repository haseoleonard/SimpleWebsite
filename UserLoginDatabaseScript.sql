USE [master]
GO
/****** Object:  Database [UserLogin]    Script Date: 7/15/2020 3:01:47 PM ******/
CREATE DATABASE [UserLogin]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UserLogin', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\UserLogin.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UserLogin_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\UserLogin_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UserLogin] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UserLogin].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UserLogin] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UserLogin] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UserLogin] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UserLogin] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UserLogin] SET ARITHABORT OFF 
GO
ALTER DATABASE [UserLogin] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [UserLogin] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [UserLogin] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UserLogin] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UserLogin] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UserLogin] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UserLogin] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UserLogin] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UserLogin] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UserLogin] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UserLogin] SET  DISABLE_BROKER 
GO
ALTER DATABASE [UserLogin] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UserLogin] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UserLogin] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UserLogin] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UserLogin] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UserLogin] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UserLogin] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UserLogin] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [UserLogin] SET  MULTI_USER 
GO
ALTER DATABASE [UserLogin] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UserLogin] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UserLogin] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UserLogin] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [UserLogin]
GO
/****** Object:  Table [dbo].[OrderDetails]    Script Date: 7/15/2020 3:01:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[OrderID] [int] NULL,
	[ProductName] [varchar](50) NULL,
	[Quantity] [int] NULL
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 7/15/2020 3:01:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerName] [varchar](50) NULL,
	[CustomerAddress] [varchar](50) NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Products]    Script Date: 7/15/2020 3:01:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Products](
	[ProductName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 7/15/2020 3:01:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](30) NULL,
	[lastName] [nvarchar](50) NULL,
	[isAdmin] [bit] NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[OrderDetails] ([OrderID], [ProductName], [Quantity]) VALUES (1, N'Java', 5)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductName], [Quantity]) VALUES (1, N'NetBeans', 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductName], [Quantity]) VALUES (1003, N'Java', 2)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductName], [Quantity]) VALUES (1003, N'JSP', 1)
INSERT [dbo].[OrderDetails] ([OrderID], [ProductName], [Quantity]) VALUES (1003, N'Servlet', 1)
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [CustomerName], [CustomerAddress]) VALUES (1, N'a', N'hcm')
INSERT [dbo].[Orders] ([OrderID], [CustomerName], [CustomerAddress]) VALUES (2, N'Haseo', N'HCM')
INSERT [dbo].[Orders] ([OrderID], [CustomerName], [CustomerAddress]) VALUES (1003, N'haseo', N'hcm')
SET IDENTITY_INSERT [dbo].[Orders] OFF
INSERT [dbo].[Products] ([ProductName]) VALUES (N'Java')
INSERT [dbo].[Products] ([ProductName]) VALUES (N'JavaBean')
INSERT [dbo].[Products] ([ProductName]) VALUES (N'JSP')
INSERT [dbo].[Products] ([ProductName]) VALUES (N'NetBeans')
INSERT [dbo].[Products] ([ProductName]) VALUES (N'Servlet')
INSERT [dbo].[Products] ([ProductName]) VALUES (N'TomCat')
INSERT [dbo].[Users] ([username], [password], [lastName], [isAdmin]) VALUES (N'abcdef', N'abcdef', N'Đào Hữu Nghĩa', 0)
INSERT [dbo].[Users] ([username], [password], [lastName], [isAdmin]) VALUES (N'dao.huunghia2602', N'abcdef', N'Đào Hữu Nghĩa', 0)
INSERT [dbo].[Users] ([username], [password], [lastName], [isAdmin]) VALUES (N'haseoleonard', N'28071998', N'HaseoLeonard', 1)
INSERT [dbo].[Users] ([username], [password], [lastName], [isAdmin]) VALUES (N'minhthy', N'28071998', N'Ha Le Minh Thy', 1)
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Orders]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Products] FOREIGN KEY([ProductName])
REFERENCES [dbo].[Products] ([ProductName])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Products]
GO
USE [master]
GO
ALTER DATABASE [UserLogin] SET  READ_WRITE 
GO
