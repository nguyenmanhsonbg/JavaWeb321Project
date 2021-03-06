USE [master]
GO
/****** Object:  Database [QLVTVLXD]    Script Date: 11/8/2021 3:05:00 PM ******/
CREATE DATABASE [QLVTVLXD]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QLVTVLXD', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLVTVLXD.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'QLVTVLXD_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.MSSQLSERVER\MSSQL\DATA\QLVTVLXD_log.ldf' , SIZE = 73728KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [QLVTVLXD] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLVTVLXD].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLVTVLXD] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLVTVLXD] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLVTVLXD] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLVTVLXD] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLVTVLXD] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLVTVLXD] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [QLVTVLXD] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLVTVLXD] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLVTVLXD] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLVTVLXD] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLVTVLXD] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLVTVLXD] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLVTVLXD] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLVTVLXD] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLVTVLXD] SET  DISABLE_BROKER 
GO
ALTER DATABASE [QLVTVLXD] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLVTVLXD] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLVTVLXD] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLVTVLXD] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLVTVLXD] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLVTVLXD] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLVTVLXD] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLVTVLXD] SET RECOVERY FULL 
GO
ALTER DATABASE [QLVTVLXD] SET  MULTI_USER 
GO
ALTER DATABASE [QLVTVLXD] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLVTVLXD] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLVTVLXD] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLVTVLXD] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLVTVLXD] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QLVTVLXD', N'ON'
GO
ALTER DATABASE [QLVTVLXD] SET QUERY_STORE = OFF
GO
USE [QLVTVLXD]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Categories]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Categories](
	[CategoryID] [int] IDENTITY(1,1) NOT NULL,
	[CategoryName] [nvarchar](255) NULL,
	[Description] [nvarchar](255) NULL,
	[Picture] [nvarchar](max) NULL,
 CONSTRAINT [PK_Categories] PRIMARY KEY CLUSTERED 
(
	[CategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customers]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customers](
	[CustomerID] [int] IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](50) NULL,
	[ContactName] [nvarchar](50) NULL,
	[Address] [nvarchar](255) NULL,
	[Phone] [int] NULL,
	[Unpaid] [float] NULL,
	[Paid] [float] NULL,
 CONSTRAINT [PK_Customers] PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feature]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feature](
	[fid] [int] NOT NULL,
	[url] [nvarchar](max) NULL,
 CONSTRAINT [PK_Feature] PRIMARY KEY CLUSTERED 
(
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FeatureGroup]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FeatureGroup](
	[fid] [int] NOT NULL,
	[gid] [int] NOT NULL,
 CONSTRAINT [PK_FeatureGroup] PRIMARY KEY CLUSTERED 
(
	[fid] ASC,
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Good Import Details]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Good Import Details](
	[GoodImportID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[UnitPrice] [float] NULL,
	[Quantity] [float] NULL,
	[Place] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Good Import Details] PRIMARY KEY CLUSTERED 
(
	[GoodImportID] ASC,
	[ProductID] ASC,
	[Place] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Good Imports]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Good Imports](
	[GoodImportID] [int] IDENTITY(1,1) NOT NULL,
	[SupplierID] [int] NULL,
	[Date] [date] NULL,
	[Picture] [nvarchar](50) NULL,
	[Note] [nvarchar](max) NULL,
	[value] [float] NULL,
 CONSTRAINT [PK_Good Imports] PRIMARY KEY CLUSTERED 
(
	[GoodImportID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[id] [int] NOT NULL,
	[name] [nvarchar](255) NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GroupAccount]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GroupAccount](
	[gid] [int] NOT NULL,
	[username] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_GroupAccount] PRIMARY KEY CLUSTERED 
(
	[gid] ASC,
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order Details]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order Details](
	[OrderID] [int] NOT NULL,
	[ProductID] [int] NOT NULL,
	[UnitPrice] [float] NULL,
	[Quantity] [float] NULL,
	[Place] [nvarchar](255) NOT NULL,
 CONSTRAINT [PK_Order Details] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC,
	[ProductID] ASC,
	[Place] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[OrderID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [int] NULL,
	[Date] [date] NULL,
	[Picture] [nvarchar](max) NULL,
	[Note] [nvarchar](max) NULL,
	[value] [float] NULL,
 CONSTRAINT [PK_Orders] PRIMARY KEY CLUSTERED 
(
	[OrderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PaidHistory]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PaidHistory](
	[CustomerID] [int] NULL,
	[Action] [nvarchar](50) NULL,
	[Value] [float] NULL,
	[Paid] [float] NULL,
	[Unpaid] [float] NULL,
	[Date] [date] NULL,
	[id] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_PaidHistory] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Products]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Products](
	[ProductID] [int] IDENTITY(1,1) NOT NULL,
	[ProductName] [nvarchar](50) NULL,
	[CategoryID] [int] NULL,
	[MeasureUnit] [nvarchar](255) NULL,
 CONSTRAINT [PK_Products] PRIMARY KEY CLUSTERED 
(
	[ProductID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Suppliers]    Script Date: 11/8/2021 3:05:00 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Suppliers](
	[SupplierID] [int] IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](255) NULL,
	[ContactName] [nvarchar](255) NULL,
	[Address] [nvarchar](max) NULL,
	[Note] [nvarchar](max) NULL,
	[Phone] [int] NULL,
	[Paid] [float] NULL,
	[Unpaid] [float] NULL,
 CONSTRAINT [PK_Suppliers] PRIMARY KEY CLUSTERED 
(
	[SupplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password]) VALUES (N'manhnguyen', N'123')
GO
SET IDENTITY_INSERT [dbo].[Categories] ON 

INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Picture]) VALUES (1, N'Gạch', NULL, NULL)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Picture]) VALUES (2, N'Cát', NULL, NULL)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Picture]) VALUES (3, N'Xi măng', NULL, NULL)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Picture]) VALUES (4, N'Đất', NULL, NULL)
INSERT [dbo].[Categories] ([CategoryID], [CategoryName], [Description], [Picture]) VALUES (5, N'Đá', NULL, NULL)
SET IDENTITY_INSERT [dbo].[Categories] OFF
GO
SET IDENTITY_INSERT [dbo].[Customers] ON 

INSERT [dbo].[Customers] ([CustomerID], [CompanyName], [ContactName], [Address], [Phone], [Unpaid], [Paid]) VALUES (1, N'Chien Beo', N'', N'Yen Dung - Bac Giang', 1234555, 201654909, 6200000)
INSERT [dbo].[Customers] ([CustomerID], [CompanyName], [ContactName], [Address], [Phone], [Unpaid], [Paid]) VALUES (2, N'Hương Sinh', NULL, N'Xuân Trung - Xuân Phú', 1234567, 36400000, 0)
INSERT [dbo].[Customers] ([CustomerID], [CompanyName], [ContactName], [Address], [Phone], [Unpaid], [Paid]) VALUES (3, N'Huế Cát', NULL, N'Xuân Đông - Xuân Phú', 123349559, 401000000, 0)
INSERT [dbo].[Customers] ([CustomerID], [CompanyName], [ContactName], [Address], [Phone], [Unpaid], [Paid]) VALUES (4, N'Duong lu', NULL, N'Thôn 8 - Thạch Hòa - Thạch Thất - Hà Nội', 2222, 2002000000, 0)
SET IDENTITY_INSERT [dbo].[Customers] OFF
GO
INSERT [dbo].[Feature] ([fid], [url]) VALUES (1, N'/CustomerController')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (2, N'/ListCustomer')
INSERT [dbo].[Feature] ([fid], [url]) VALUES (3, N'/EditCustomer')
GO
INSERT [dbo].[FeatureGroup] ([fid], [gid]) VALUES (1, 1)
INSERT [dbo].[FeatureGroup] ([fid], [gid]) VALUES (2, 1)
INSERT [dbo].[FeatureGroup] ([fid], [gid]) VALUES (3, 1)
GO
INSERT [dbo].[Group] ([id], [name]) VALUES (1, N'Admin')
INSERT [dbo].[Group] ([id], [name]) VALUES (2, N'User')
GO
INSERT [dbo].[GroupAccount] ([gid], [username]) VALUES (1, N'manhnguyen')
GO
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (1, 5, 160000, 4, N'Làng Chỗ')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (1, 7, 950000, 2.5, N'Làng Chỗ')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (34, 1, 200, 2000, N'Làng Xuân Đông')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (35, 1, 2000, 2000, N'Xuân Thượng')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (35, 1, 300, 2000, N'Xuân Trung')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (36, 1, 2000, 2000, N'Làng Kình')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (36, 1, 2000, 10000, N'Làng Võng')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (36, 5, 200, 20000, N'Làng Trung')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (37, 1, 100, 1000, N'Làng Dõng')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (37, 1, 2000, 2000, N'Làng Đông')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (37, 1, 2000, 20000, N'Làng Kình')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (39, 1, 200, 20000, N'Làng Vòng')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (42, 1, 200, 2000, N'Làng Đông')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (42, 1, 20000, 2000, N'Làng Dung')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (43, 1, 20000, 20000, N'Làng Đông')
INSERT [dbo].[Order Details] ([OrderID], [ProductID], [UnitPrice], [Quantity], [Place]) VALUES (43, 1, 500, 2000, N'Làng Dung')
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (1, 1, CAST(N'2020-07-06' AS Date), NULL, NULL, 3015000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (8, 1, CAST(N'2021-03-28' AS Date), NULL, NULL, 1)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (10, 1, CAST(N'2021-03-18' AS Date), NULL, NULL, 200024)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (17, 2, CAST(N'2021-03-29' AS Date), NULL, NULL, 4000000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (20, 1, CAST(N'2021-04-01' AS Date), NULL, NULL, 4600000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (31, 1, CAST(N'2021-03-29' AS Date), NULL, NULL, 4884)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (33, 4, CAST(N'2021-03-31' AS Date), NULL, NULL, 2002000000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (34, 2, CAST(N'2021-03-31' AS Date), NULL, NULL, 400000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (35, 1, CAST(N'2021-03-30' AS Date), NULL, NULL, 4600000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (36, 2, CAST(N'2021-04-08' AS Date), NULL, NULL, 28000000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (37, 1, CAST(N'2021-03-30' AS Date), NULL, NULL, 44100000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (39, 2, CAST(N'2021-04-08' AS Date), NULL, NULL, 4000000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (42, 1, CAST(N'2021-04-09' AS Date), NULL, NULL, 40400000)
INSERT [dbo].[Orders] ([OrderID], [CustomerID], [Date], [Picture], [Note], [value]) VALUES (43, 3, CAST(N'2021-04-07' AS Date), NULL, NULL, 401000000)
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[PaidHistory] ON 

INSERT [dbo].[PaidHistory] ([CustomerID], [Action], [Value], [Paid], [Unpaid], [Date], [id]) VALUES (1, NULL, NULL, 2000000, NULL, CAST(N'2021-03-29' AS Date), 25)
INSERT [dbo].[PaidHistory] ([CustomerID], [Action], [Value], [Paid], [Unpaid], [Date], [id]) VALUES (1, NULL, NULL, 200000, NULL, CAST(N'1900-01-01' AS Date), 26)
INSERT [dbo].[PaidHistory] ([CustomerID], [Action], [Value], [Paid], [Unpaid], [Date], [id]) VALUES (1, NULL, NULL, 4000000, NULL, CAST(N'1900-01-01' AS Date), 27)
SET IDENTITY_INSERT [dbo].[PaidHistory] OFF
GO
SET IDENTITY_INSERT [dbo].[Products] ON 

INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (1, N'Gạch đỏ', 1, N'viên')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (2, N'Gạch thường', 1, N'viên')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (3, N'Gạch lỗ', 1, N'viên')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (4, N'Cát xây', 2, N'm3')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (5, N'Cát chát ', 2, N'm3')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (6, N'Cát đổ nền', 2, N'm3')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (7, N'Xi xây', 3, N'tấn')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (8, N'Xi đổ', 3, N'tấn')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (9, N'Đất núi', 4, N'm3')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (10, N'Đất màu', 4, N'm3')
INSERT [dbo].[Products] ([ProductID], [ProductName], [CategoryID], [MeasureUnit]) VALUES (11, N'Đá đổ ', 5, N'm3')
SET IDENTITY_INSERT [dbo].[Products] OFF
GO
ALTER TABLE [dbo].[FeatureGroup]  WITH CHECK ADD  CONSTRAINT [FK_FeatureGroup_Feature] FOREIGN KEY([fid])
REFERENCES [dbo].[Feature] ([fid])
GO
ALTER TABLE [dbo].[FeatureGroup] CHECK CONSTRAINT [FK_FeatureGroup_Feature]
GO
ALTER TABLE [dbo].[FeatureGroup]  WITH CHECK ADD  CONSTRAINT [FK_FeatureGroup_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([id])
GO
ALTER TABLE [dbo].[FeatureGroup] CHECK CONSTRAINT [FK_FeatureGroup_Group]
GO
ALTER TABLE [dbo].[Good Import Details]  WITH CHECK ADD  CONSTRAINT [FK_Good Import Details_Good Imports] FOREIGN KEY([GoodImportID])
REFERENCES [dbo].[Good Imports] ([GoodImportID])
GO
ALTER TABLE [dbo].[Good Import Details] CHECK CONSTRAINT [FK_Good Import Details_Good Imports]
GO
ALTER TABLE [dbo].[Good Import Details]  WITH CHECK ADD  CONSTRAINT [FK_Good Import Details_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[Good Import Details] CHECK CONSTRAINT [FK_Good Import Details_Products]
GO
ALTER TABLE [dbo].[Good Imports]  WITH CHECK ADD  CONSTRAINT [FK_Good Imports_Suppliers] FOREIGN KEY([SupplierID])
REFERENCES [dbo].[Suppliers] ([SupplierID])
GO
ALTER TABLE [dbo].[Good Imports] CHECK CONSTRAINT [FK_Good Imports_Suppliers]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_Account]
GO
ALTER TABLE [dbo].[GroupAccount]  WITH CHECK ADD  CONSTRAINT [FK_GroupAccount_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([id])
GO
ALTER TABLE [dbo].[GroupAccount] CHECK CONSTRAINT [FK_GroupAccount_Group]
GO
ALTER TABLE [dbo].[Order Details]  WITH CHECK ADD  CONSTRAINT [FK_Order Details_Orders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[Orders] ([OrderID])
GO
ALTER TABLE [dbo].[Order Details] CHECK CONSTRAINT [FK_Order Details_Orders]
GO
ALTER TABLE [dbo].[Order Details]  WITH CHECK ADD  CONSTRAINT [FK_Order Details_Products] FOREIGN KEY([ProductID])
REFERENCES [dbo].[Products] ([ProductID])
GO
ALTER TABLE [dbo].[Order Details] CHECK CONSTRAINT [FK_Order Details_Products]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Customers]
GO
ALTER TABLE [dbo].[PaidHistory]  WITH CHECK ADD  CONSTRAINT [FK_PaidHistory_Customers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[Customers] ([CustomerID])
GO
ALTER TABLE [dbo].[PaidHistory] CHECK CONSTRAINT [FK_PaidHistory_Customers]
GO
ALTER TABLE [dbo].[Products]  WITH CHECK ADD  CONSTRAINT [FK_Products_Categories] FOREIGN KEY([CategoryID])
REFERENCES [dbo].[Categories] ([CategoryID])
GO
ALTER TABLE [dbo].[Products] CHECK CONSTRAINT [FK_Products_Categories]
GO
USE [master]
GO
ALTER DATABASE [QLVTVLXD] SET  READ_WRITE 
GO
