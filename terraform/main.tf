
data "aws_ami" "linux_ami" {
  most_recent = true
  owners      = ["amazon"] # Or the specific account ID like "099720109477" for Canonical

  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"] # Use wildcards (*) for versioning
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

# Define the Security Group (allow HTTP and SSH)
resource "aws_security_group" "only_80_ssh" {
  name        = "url-shortener-sg"
  description = "Allow HTTP (port 80) and SSH (port 22) inbound traffic"
  # Ingress rule for HTTP (port 80) from anywhere
  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  # Ingress rule for SSH (port 22) for management (optional, adjust cidr_blocks as needed)
  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  # Egress rule (allow all outbound traffic) - security groups are stateful, but explicit egress is a common practice
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
  tags = {
    Name = var.instance_name
  }
}

resource "aws_instance" "app_server" {
  ami           = data.aws_ami.linux_ami.id #"ami-0ebf411a80b6b22cb"  #data.aws_ami.ubuntu.id
  instance_type = var.instance_type
  user_data = file("${path.module}/user_data.sh")
  vpc_security_group_ids = [aws_security_group.only_80_ssh.id]
  tags = {
    Name = var.instance_name
  }
}

