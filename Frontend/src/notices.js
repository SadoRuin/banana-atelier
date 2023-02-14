export function getNotices() {
  return [
    {
      id: 1,
      notice_title: '공지 제목1',
      notice_content: '공지 내용 와랄라 1'
    },
    {
      id: 2,
      notice_title: '공지 제목2',
      notice_content: '공지 내용 와랄라 2'
    },
    {
      id: 3,
      notice_title: '공지 제목3',
      notice_content: '공지 내용 와랄라 3'
    },
    {
      id: 4,
      notice_title: '공지 제목4',
      notice_content: '공지 내용 와랄라 4'
    }
  ]
}

export function getNotice(id) {
  const notices = [
    {
      id: 1,
      notice_title: '공지 제목1',
      notice_content: '공지 내용 와랄라 1'
    },
    {
      id: 2,
      notice_title: '공지 제목2',
      notice_content: '공지 내용 와랄라 2'
    },
    {
      id: 3,
      notice_title: '공지 제목3',
      notice_content: '공지 내용 와랄라 3'
    },
    {
      id: 4,
      notice_title: '공지 제목4',
      notice_content: '공지 내용 와랄라 4'
    }
  ];

  let notice = notices.find((notice) => {return (notice.id) === id});

  // notices.find
  return notice ?? null;
}