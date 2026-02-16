variable "instance_name" {
  description = "Value of the EC2 instance's Name tag."
  type        = string
  default     = "tinyurl-server"
}

variable "instance_type" {
  description = "The EC2 instance's type."
  type        = string
  default     = "t3.medium"
}

variable "aws_region" {
  description = "AWS region to use"
  type        = string
  default     = "us-east-1"
}