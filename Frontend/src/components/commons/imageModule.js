const IMG_BASE_URL = 'https://i8a108.s3.ap-northeast-2.amazonaws.com';

export function checkIsDefaultProfile (url, userSeq) {
  if (url?.slice(0, 15) === 'default_profile') {
    return `${IMG_BASE_URL}/default-profile/${url}`
  }
  return `${IMG_BASE_URL}/profile/${userSeq}/${url}`
}

export function getArtThumbnail (url, userSeq) {
  return `${IMG_BASE_URL}/art/thumbnail/${userSeq}/${url}`
}

export function getArtImage (url, userSeq) {
  return `${IMG_BASE_URL}/art/${userSeq}/${url}`
}